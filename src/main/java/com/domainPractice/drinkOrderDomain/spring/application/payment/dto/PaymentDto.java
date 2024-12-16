package com.domainPractice.drinkOrderDomain.spring.application.payment.dto;

import com.domainPractice.drinkOrderDomain.spring.domain.payment.Payment;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.PaymentMethod;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentDto {

    private PaymentMethod paymentMethod; // 결제 방법
    private PaymentStatus paymentStatus; // 결제 상태

    public PaymentDto(PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public Payment toEntity() {
        return new Payment(paymentMethod, paymentStatus);
    }
}
