package com.domainPractice.drinkOrderDomain.spring.application.payment;

import com.domainPractice.drinkOrderDomain.spring.application.order.OrderService;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.spring.domain.order.OrderId;
import com.domainPractice.drinkOrderDomain.spring.domain.order.OrderLine;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.Payment;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.PaymentRepository;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.PaymentStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
