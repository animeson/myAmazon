package com.amazon.svistun.service.sales.customerService;

import com.amazon.svistun.dto.sales.CustomerDto;

import com.amazon.svistun.entity.sales.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    void createClient(CustomerDto customerDto);
    Customer getClientById(Long id);
}
