package com.domainPractice.drinkOrderDomain.application.order;

import com.domainPractice.drinkOrderDomain.common.exceptions.OrderException;
import com.domainPractice.drinkOrderDomain.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.domain.order.Order;
import com.domainPractice.drinkOrderDomain.domain.order.OrderId;
import com.domainPractice.drinkOrderDomain.domain.order.OrderLine;
import com.domainPractice.drinkOrderDomain.domain.order.OrderRepository;
import com.domainPractice.drinkOrderDomain.domain.order.OrderStatus;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.*;
import com.domainPractice.drinkOrderDomain.domain.drink.DrinkRepository;
import com.domainPractice.drinkOrderDomain.spring.domain.order.*;
import com.domainPractice.drinkOrderDomain.domain.payment.Payment;
import com.domainPractice.drinkOrderDomain.domain.payment.PaymentRepository;
import com.domainPractice.drinkOrderDomain.domain.staff.Staff;
import com.domainPractice.drinkOrderDomain.domain.staff.StaffRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final StaffRepository staffRepository;
    private final DrinkRepository drinkRepository;

    public OrderService(OrderRepository orderRepository,
                        PaymentRepository paymentRepository,
                        StaffRepository staffRepository,
                        DrinkRepository drinkRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.staffRepository = staffRepository;
        this.drinkRepository = drinkRepository;
    }

    /**
     * 주문 생성
     */
    public Order createOrder(CustomerId customerId, List<OrderLine> orderLines, Payment payment) {
        // 1. 음료 재고 확인
        for (OrderLine orderLine : orderLines) {
            orderLine.performStockValidation(drinkRepository);
        }

        // 2. 주문 생성
        // TODO 자동증가 값은 생성자에서 빼도 된다.
        Order order = new Order(customerId, LocalDateTime.now());
        for (OrderLine orderLine : orderLines) {
            order.addOrderLine(orderLine);
        }

        // 3. 결제 상태에 따른 주문 상태 설정
        order.updateStatusBasedOnPayment(payment);

        // 4. 결제상태가 어떻든 간에 주문은 저장
        orderRepository.save(order);

        // 5. 결제 저장
        paymentRepository.save(payment);

        return order;
    }

    /**
     * 주문 상태 변경
     */
    public Order changeOrderStatus(OrderId orderId, OrderStatus newStatus, Long staffId) {
        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new OrderException("주문정보를 찾지 못했습니다."));

        Staff staff = staffRepository.findStaffById(staffId)
                .orElseThrow(() -> new OrderException("직원정보를 찾지 못했습니다."));

        // 상태 전환 및 검증
        order.validateStatusTransition(newStatus, staff);

        // 상태 변경
        order.changeStatus(newStatus);

        return orderRepository.save(order);
    }

    public List<OrderLine> orderLinesOfOrderId(OrderId orderId) {
        return orderRepository.findOrderLinesByOrderId(orderId);
    }

}