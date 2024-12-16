package com.domainPractice.drinkOrderDomain.infrastructure.persistence.payment;

import com.domainPractice.drinkOrderDomain.domain.payment.Payment;
import com.domainPractice.drinkOrderDomain.domain.payment.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, Long>, PaymentRepository {
}
