package com.christian.ecommerce.exceptions;

public class CustomersException extends RuntimeException {
    private final String reason;

    public CustomersException(String message) {
        super(message);
        this.reason = message;
    }

    public String getReason(){
        return reason;
    }
}
