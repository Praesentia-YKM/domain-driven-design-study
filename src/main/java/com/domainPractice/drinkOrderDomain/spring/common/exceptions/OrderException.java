package com.domainPractice.drinkOrderDomain.spring.common.exceptions;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
