package com.domainPractice.drinkOrderDomain.common.exceptions;

public class PayException extends RuntimeException {
    public PayException(String message) {
        super(message);
    }
}