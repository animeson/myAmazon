package com.amazon.svistun.service.sales.customerService;

import com.amazon.svistun.dto.sales.CustomerDto;
import com.amazon.svistun.entity.sales.Customer;
import com.amazon.svistun.repository.sales.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void createClient(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customerRepository.save(customer);
    }

    @Override
    public Customer getClientById(Long id) {
        return customerRepository.getClientByCustomerId(id);
    }
}
