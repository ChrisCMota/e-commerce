package com.christian.ecommerce.exceptions;

public class ProductException extends RuntimeException {

    private final String reason;

    public ProductException(String message) {
        super(message);
        this.reason = message;
    }

    public String getReason(){
        return reason;
    }
}
