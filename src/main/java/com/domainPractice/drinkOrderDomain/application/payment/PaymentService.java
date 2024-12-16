package com.domainPractice.drinkOrderDomain.application.payment;

import com.domainPractice.drinkOrderDomain.application.order.OrderService;
import com.domainPractice.drinkOrderDomain.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.domain.order.OrderId;
import com.domainPractice.drinkOrderDomain.domain.order.OrderLine;
import com.domainPractice.drinkOrderDomain.domain.payment.Payment;
import com.domainPractice.drinkOrderDomain.domain.payment.PaymentRepository;
import com.domainPractice.drinkOrderDomain.domain.payment.PaymentStatus;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public PaymentService(PaymentRepository paymentRepository, OrderService orderService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    /**
     * 결제 처리
     */
    // TODO PaymentService에서 orderService를 바라본다는건 DDD원칙에 어긋난다? 같은 계층을 본다는게?
    // TODO 도메인 자체가 의존해 있는 거 아님? 결제를 하면 주문 들어가라매? => 이벤트 패턴이란게 있다.
    public Payment processPayment(CustomerId customerId, OrderId orderId, Payment payment) {
        // 1. 결제 저장
        Payment savedPayment = paymentRepository.save(payment);

        // 2. 결제가 성공적으로 완료되었는지 확인
        if (payment.getPaymentStatus() == PaymentStatus.PAID) {
            // 3. 주문 상태를 변경
            List<OrderLine> orderLines = orderService.orderLinesOfOrderId(orderId);
            orderService.createOrder(customerId, orderLines, payment);
        }

        return savedPayment;
    }
}
