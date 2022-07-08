package com.amazon.svistun.service.sales.customerService;

import com.amazon.svistun.dto.sales.CustomerDto;

import com.amazon.svistun.entity.sales.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer createClient(CustomerDto customerDto);
    Customer getClientById(Long id);
    Customer updateClient(Long id, CustomerDto customerDto);
}
