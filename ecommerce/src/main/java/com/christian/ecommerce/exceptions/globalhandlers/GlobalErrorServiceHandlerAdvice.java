package com.christian.ecommerce.exceptions.globalhandlers;

import com.christian.ecommerce.exceptions.CustomersException;
import com.christian.ecommerce.exceptions.VariantProductException;
import com.christian.ecommerce.exceptions.recordsexceptions.ServiceExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorServiceHandlerAdvice {

    @ExceptionHandler(CustomersException.class)
    public ResponseEntity<ServiceExceptionMessage> handleServiceException(CustomersException ex){
        var error = new ServiceExceptionMessage(HttpStatus.BAD_REQUEST.value(), ex.getReason());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(VariantProductException.class)
    public ResponseEntity<ServiceExceptionMessage> handleVariantServiceException(VariantProductException ex){
        var error = new ServiceExceptionMessage(HttpStatus.BAD_REQUEST.value(), ex.getReason());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // IT WORKS. BUT I DO NOT WANT TO USE SO I CAN SEE THE ERROR
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<ServiceExceptionMessage> handleSQLIntegrityException(SQLIntegrityConstraintViolationException ex){
//        var error = new ServiceExceptionMessage(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage());
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
}
