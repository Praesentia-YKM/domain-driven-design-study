package com.domainPractice.drinkOrderDomain.spring.domain.order;

import com.domainPractice.drinkOrderDomain.spring.common.exceptions.OrderException;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.Payment;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.PaymentStatus;
import com.domainPractice.drinkOrderDomain.spring.domain.staff.Staff;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Order {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private OrderId id;

    @Embedded
    private CustomerId customerId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderTime;

    private BigDecimal totalAmount;

    public Order(OrderId id, CustomerId customerId, LocalDateTime orderTime) {
        this.id = id;
        this.customerId = customerId;
        this.status = OrderStatus.PENDING;
        this.orderTime = orderTime != null ? orderTime : LocalDateTime.now();
        this.totalAmount = BigDecimal.ZERO;
    }

    public void addOrderLine(OrderLine orderLine) {
        if (orderLine == null || orderLine.getTotalPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderException("유효하지 않은 결제선 입니다.");
        }

        this.orderLines.add(orderLine);
        this.totalAmount = this.totalAmount.add(orderLine.getTotalPrice());
    }

    public void changeStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * 결제 상태에 따라 주문 상태 설정
     */
    public void updateStatusBasedOnPayment(Payment payment) {
        this.status = switch (payment.getPaymentStatus()) {
            case UNPAID -> OrderStatus.PENDING;
            case PAID -> OrderStatus.IN_PROGRESS;
        };
    }

    public void validateStatusTransition(OrderStatus newStatus, Staff staff) {
        // 상태 전환 검증
        if (!this.status.isTransitionAllowed(newStatus)) {
            throw new OrderException("잘못된 상태 전환: " + this.status + " → " + newStatus);
        }

        // 직원 권한 검증
        if (!staff.canStaffChangeStatus(staff.getRole(), this.status, newStatus)) {
            throw new OrderException("직원(" + staff.getRole() + ")은 " + this.status + "에서 " + newStatus + "로 전환할 수 없습니다.");
        }
    }

}

