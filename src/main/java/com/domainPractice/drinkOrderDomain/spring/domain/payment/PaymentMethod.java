package com.domainPractice.drinkOrderDomain.spring.domain.payment;

public enum PaymentMethod {
    CARD("카드 결제"),
    CASH("현금 결제"),
    APP("앱 결제");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String description() {
        return this.description;
    }
}