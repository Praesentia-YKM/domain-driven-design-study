package com.domainPractice.drinkOrderDomain.spring.common.exceptions;

public class PayException extends RuntimeException {
    public PayException(String message) {
        super(message);
    }
}