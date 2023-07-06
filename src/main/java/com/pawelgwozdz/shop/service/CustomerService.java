package com.pawelgwozdz.shop.service;

import com.pawelgwozdz.shop.entity.Customer;

public interface CustomerService {

    Customer findByIdOrThrow(Long id);
}
