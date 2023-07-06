package com.pawelgwozdz.shop.service.impl;

import com.pawelgwozdz.shop.entity.Customer;
import com.pawelgwozdz.shop.enums.ExceptionMessage;
import com.pawelgwozdz.shop.exceptions.CustomerNotFoundException;
import com.pawelgwozdz.shop.repository.CustomersRepository;
import com.pawelgwozdz.shop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomersRepository customersRepository;

    @Override
    public Customer findByIdOrThrow(Long id) {
        return customersRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(ExceptionMessage.CUSTOMER_NOT_FOUND, id));
    }
}
