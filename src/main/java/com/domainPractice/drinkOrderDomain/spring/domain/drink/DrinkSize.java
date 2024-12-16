package com.domainPractice.drinkOrderDomain.spring.domain.drink;

import java.math.BigDecimal;

public enum DrinkSize {
    SMALL(BigDecimal.valueOf(0.8)),
    MEDIUM(BigDecimal.valueOf(1.0)),
    LARGE(BigDecimal.valueOf(1.2));

    private final BigDecimal multiplier;

    DrinkSize(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }

    public BigDecimal adjustPrice(BigDecimal basePrice) {
        return basePrice.multiply(multiplier);
    }
}