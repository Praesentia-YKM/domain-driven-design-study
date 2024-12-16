package com.domainPractice.drinkOrderDomain.spring.infrastructure.persistence.payment;

import com.domainPractice.drinkOrderDomain.spring.domain.payment.Payment;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, Long>, PaymentRepository {
}
