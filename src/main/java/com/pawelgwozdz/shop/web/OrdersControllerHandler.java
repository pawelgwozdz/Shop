package com.pawelgwozdz.shop.web;

import com.pawelgwozdz.shop.dto.ErrorResponse;
import com.pawelgwozdz.shop.exceptions.CustomerNotFoundException;
import com.pawelgwozdz.shop.exceptions.NotEnoughProductsException;
import com.pawelgwozdz.shop.exceptions.OrderNotFoundException;
import com.pawelgwozdz.shop.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrdersControllerHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(CustomerNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .name(ex.getExceptionMessage().getName())
                .title(ex.getExceptionMessage().getTitle())
                .reason(ex.getExceptionMessage().getReason())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(NotEnoughProductsException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .name(ex.getExceptionMessage().getName())
                .title(ex.getExceptionMessage().getTitle())
                .reason(ex.getExceptionMessage().getReason())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(OrderNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .name(ex.getExceptionMessage().getName())
                .title(ex.getExceptionMessage().getTitle())
                .reason(ex.getExceptionMessage().getReason())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(ProductNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .name(ex.getExceptionMessage().getName())
                .title(ex.getExceptionMessage().getTitle())
                .reason(ex.getExceptionMessage().getReason())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(Exception ex) {


        return null;
    }
}
