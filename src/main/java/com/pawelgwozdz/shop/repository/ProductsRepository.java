package com.pawelgwozdz.shop.repository;

import com.pawelgwozdz.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdIn(List<Long> id);
}
