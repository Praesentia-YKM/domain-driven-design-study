package com.domainPractice.drinkOrderDomain.spring.domain.payment;

import com.domainPractice.drinkOrderDomain.spring.domain.order.OrderId;

import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findPaymentByOrderId(OrderId id);
}
