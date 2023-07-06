package com.pawelgwozdz.shop.exceptions;

import com.pawelgwozdz.shop.enums.ExceptionMessage;
import lombok.Getter;

public class OrderNotFoundException extends RuntimeException{

    @Getter
    private final ExceptionMessage exceptionMessage;

    public OrderNotFoundException(ExceptionMessage exceptionMessage, long id) {
        super(exceptionMessage.getTitle() + " Order id: " + id);
        this.exceptionMessage = exceptionMessage;
    }
}
