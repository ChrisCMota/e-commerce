package com.christian.ecommerce.exceptions;

public class CategoryException extends RuntimeException{
    private final String reason;

    public CategoryException(String message){
        super(message);
        this.reason = message;
    }

    public String getReason(){
        return reason;
    }
}
