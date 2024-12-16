package com.domainPractice.drinkOrderDomain.infrastructure.persistence.customer;

import com.domainPractice.drinkOrderDomain.domain.customer.Customer;
import com.domainPractice.drinkOrderDomain.domain.customer.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long>, CustomerRepository {

}
