package com.domainPractice.drinkOrderDomain.domain.staff;

public enum Role {
    BARISTA("바리스타"),
    CASHIER("계산원");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
