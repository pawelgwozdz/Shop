package com.pawelgwozdz.shop.repository;

import com.pawelgwozdz.shop.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductsRepository extends JpaRepository<OrderedProduct, Long> {
    List<OrderedProduct> findByOrderId(Long id);
}
