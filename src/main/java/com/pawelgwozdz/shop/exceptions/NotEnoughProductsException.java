package com.pawelgwozdz.shop.exceptions;

import com.pawelgwozdz.shop.enums.ExceptionMessage;
import lombok.Getter;

public class NotEnoughProductsException extends RuntimeException{

    @Getter
    private final ExceptionMessage exceptionMessage;

    public NotEnoughProductsException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getTitle());
        this.exceptionMessage = exceptionMessage;
    }
}
