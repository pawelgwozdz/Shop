package com.pawelgwozdz.shop.service.impl;

import com.pawelgwozdz.shop.entity.Product;
import com.pawelgwozdz.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Test
    void shouldReturnProducts() {
        //GIVEN
        List<Long> ids = new ArrayList<>();
        ids.add(1L);

        //WHEN
        List<Product> products = productService.findByIdsOrThrow(ids);

        //THEN
        assertEquals(1, products.size());
        assertEquals(products.get(0).getId(), 1);
        assertEquals(products.get(0).getDescription(), "Bluetooth wireless headphones");
    }

    @Test
    void shouldNotReturnProducts() {
        //GIVEN
        List<Long> ids = new ArrayList<>();
        ids.add(10L);


        //WHEN
        List<Product> products = productService.findByIdsOrThrow(ids);

        //THEN
        assertEquals(0, products.size());
    }

}