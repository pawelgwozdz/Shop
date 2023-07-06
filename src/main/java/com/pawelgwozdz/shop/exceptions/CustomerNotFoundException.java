package com.pawelgwozdz.shop.exceptions;

import com.pawelgwozdz.shop.enums.ExceptionMessage;
import lombok.Getter;

public class CustomerNotFoundException extends RuntimeException{

    @Getter
    private final ExceptionMessage exceptionMessage;

    public CustomerNotFoundException(ExceptionMessage exceptionMessage, long id) {
        super(exceptionMessage.getTitle() + " Customer id: " + id);
        this.exceptionMessage = exceptionMessage;
    }
}
