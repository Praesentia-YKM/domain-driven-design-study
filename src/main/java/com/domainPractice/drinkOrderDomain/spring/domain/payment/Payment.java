package com.domainPractice.drinkOrderDomain.spring.domain.payment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Payment(PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
}
