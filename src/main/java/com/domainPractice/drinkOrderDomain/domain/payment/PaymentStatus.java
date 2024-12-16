package com.domainPractice.drinkOrderDomain.domain.payment;

public enum PaymentStatus {
    PAID("선불 결제"),
    UNPAID("후불 결제");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String description() {
        return this.description;
    }
}
