package com.pawelgwozdz.shop.service;

import com.pawelgwozdz.shop.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> findByIdsOrThrow(List<Long> id);
    void saveAll(List<Product> products);
}
