package com.amazon.svistun.service.sales.customerService;

import com.amazon.svistun.dto.sales.CustomerDto;
import com.amazon.svistun.entity.sales.Customer;
import com.amazon.svistun.repository.sales.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer createClient(CustomerDto customerDto) {
        Customer response = null;
        Customer customer = new Customer();
        if (customerDto != null) {
            BeanUtils.copyProperties(customerDto, customer);
            response = customerRepository.save(customer);
        } else {
            log.error("You did not fill in the fields");
        }
        return response;
    }

    @Override
    public Customer getClientById(Long id) {
        return customerRepository.getClientByCustomerId(id);
    }

    @Override
    @Transactional
    public Customer updateClient(Long id, @NotNull CustomerDto customerDto) {
        Customer response = null;
        Customer customer;
        if (customerRepository.existsById(id)) {
            customer = customerRepository.getClientByCustomerId(id);
            response = customerRepository.save(updateCustomer(customer, customerDto));
        } else {
            log.error("The user with id = {} does not exist", id);
        }
        return response;
    }

    private Customer updateCustomer(Customer customer, @NotNull CustomerDto customerDto) {
        if (Objects.nonNull(customerDto.getLastName())) {
            customer.setLastName(customerDto.getLastName());
        }
        if (Objects.nonNull(customerDto.getFirstName())) {
            customer.setFirstName(customerDto.getFirstName());
        }
        if (Objects.nonNull(customerDto.getCity())) {
            customer.setCity(customerDto.getCity());
        }
        if (Objects.nonNull(customerDto.getState())) {
            customer.setStreet(customerDto.getState());
        }
        if (Objects.nonNull(customerDto.getState())) {
            customer.setState(customerDto.getState());
        }
        if (Objects.nonNull(customerDto.getZipCode())) {
            customer.setZipCode(customerDto.getZipCode());
        }
        if (Objects.nonNull(customerDto.getEmail())) {
            customer.setCity(customerDto.getEmail());
        }
        if (Objects.nonNull(customerDto.getPhone())) {
            customer.setPhone(customerDto.getPhone());
        }
        return customer;
    }


}
