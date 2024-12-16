package com.domainPractice.drinkOrderDomain.spring.infrastructure.persistence.customer;

import com.domainPractice.drinkOrderDomain.spring.domain.customer.Customer;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long>, CustomerRepository {

}
