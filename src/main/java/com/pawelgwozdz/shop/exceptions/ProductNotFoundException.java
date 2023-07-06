package com.pawelgwozdz.shop.exceptions;

import com.pawelgwozdz.shop.enums.ExceptionMessage;
import lombok.Getter;

public class ProductNotFoundException extends RuntimeException{

    @Getter
    private final ExceptionMessage exceptionMessage;

    public ProductNotFoundException(ExceptionMessage exceptionMessage, long id) {
        super(exceptionMessage.getTitle() + " Product id: " + id);
        this.exceptionMessage = exceptionMessage;
    }
}
