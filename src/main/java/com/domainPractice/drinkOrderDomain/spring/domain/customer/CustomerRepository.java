package com.domainPractice.drinkOrderDomain.spring.domain.customer;

import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId id);
}