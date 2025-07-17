package com.christian.ecommerce.exceptions;

public class VariantProductException extends RuntimeException {

    private final String reason;

    public VariantProductException(String message) {
        super(message);
        this.reason = message;
    }

    public String getReason(){
        return this.reason;
    }
}
