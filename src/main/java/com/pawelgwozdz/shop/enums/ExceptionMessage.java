package com.pawelgwozdz.shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionMessage {
    NOT_ENOUGH_PRODUCTS("Not enough Products", "Not enough Products", "Not enough Products in warehouse"),
    PRODUCT_NOT_FOUND("Product not found", "Product not found", "Product not found in warehouse"),
    CUSTOMER_NOT_FOUND("Customer not found", "Customer not found", "Customer not found in database"),
    ORDER_NOT_FOUND("Order not found", "Order not found", "Order not found in database");

    private final String title;
    private final String name;
    private final String reason;
}
