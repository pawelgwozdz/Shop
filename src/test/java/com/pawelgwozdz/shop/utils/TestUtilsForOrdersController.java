package com.pawelgwozdz.shop.utils;

import com.pawelgwozdz.shop.dto.*;
import com.pawelgwozdz.shop.entity.Product;
import com.pawelgwozdz.shop.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestUtilsForOrdersController {

    public static AddOrderDTO createAddOrderDTO() {

        List<AddOrderProductDTO> addOrderProductDTOs = new ArrayList<>();
        addOrderProductDTOs.add(AddOrderProductDTO.builder()
                .amount(3)
                .id(1l)
                .build());
        addOrderProductDTOs.add(AddOrderProductDTO.builder()
                .amount(1)
                .id(2l)
                .build());

        AddOrderDTO addOrderDTO = AddOrderDTO.builder()
                .orderDate(LocalDateTime.of(2023, 7, 5, 22, 20))
                .customerId(1l)
                .products(addOrderProductDTOs)
                .build();

        return addOrderDTO;
    }

    public static ChangeOrderStatusDTO createChangeOrderStatusDTO(long id) {
        return ChangeOrderStatusDTO.builder()
                .orderStatus(OrderStatus.PAID)
                .id(id)
                .build();
    }

    public static OrderDTO createOrderDTO(long id) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        productDTOs.add(ProductDTO.builder()
                .price(new BigDecimal("50.54"))
                .description("Bluetooth wireless headphones")
                .id(id)
                .name("HEADPHONES")
                .amount(3)
                .build());
        productDTOs.add(ProductDTO.builder()
                .price(new BigDecimal("800.23"))
                .id(2l)
                .name("PLAYSTATION 5")
                .description("New generation gaming console")
                .amount(1)
                .build());

        return OrderDTO.builder()
                .status(OrderStatus.PAID)
                .id(1l)
                .customerID(1l)
                .orderDate(LocalDateTime.of(2023, 7, 5, 22, 20))
                .orderedProducts(productDTOs)
                .wholePrice(new BigDecimal("951.85"))
                .build();
    }

    public static DeleteOrderDto createDeleteOrderDTO(Long id) {
        return DeleteOrderDto.builder()
                .id(id)
                .build();
    }
}
