package com.amazon.svistun.repository.sales;

import com.amazon.svistun.entity.sales.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer getClientByCustomerId(Long id);

}