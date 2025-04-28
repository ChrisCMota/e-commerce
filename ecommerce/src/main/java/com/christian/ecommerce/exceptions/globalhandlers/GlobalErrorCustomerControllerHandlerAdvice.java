package com.christian.ecommerce.exceptions.globalhandlers;

import com.christian.ecommerce.exceptions.recordsexceptions.IllegalArgumentExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorCustomerControllerHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<IllegalArgumentExceptionMessage> handleRequestArgumentValidation(MethodArgumentNotValidException ex){

        IllegalArgumentExceptionMessage error = new IllegalArgumentExceptionMessage(HttpStatus.BAD_REQUEST.value(), "Invalid Field(s), some fields cannot be empty or blank");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
