package com.domainPractice.drinkOrderDomain.common.exceptions;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
