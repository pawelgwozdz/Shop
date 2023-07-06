package com.pawelgwozdz.shop.service.impl;

import com.pawelgwozdz.shop.entity.Product;
import com.pawelgwozdz.shop.enums.ExceptionMessage;
import com.pawelgwozdz.shop.exceptions.ProductNotFoundException;
import com.pawelgwozdz.shop.repository.ProductsRepository;
import com.pawelgwozdz.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    @Override
    public List<Product> findByIdsOrThrow(List<Long> ids) {
        return productsRepository.findAllByIdIn(ids);
    }

    @Override
    public void saveAll(List<Product> products) {
        productsRepository.saveAll(products);
    }



}
