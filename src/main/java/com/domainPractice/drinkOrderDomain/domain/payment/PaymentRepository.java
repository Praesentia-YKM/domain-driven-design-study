package com.domainPractice.drinkOrderDomain.domain.payment;

import com.domainPractice.drinkOrderDomain.domain.order.OrderId;

import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findPaymentByOrderId(OrderId id);
}
