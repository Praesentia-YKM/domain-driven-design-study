package com.domainPractice.drinkOrderDomain.application.customer;

import com.domainPractice.drinkOrderDomain.application.customer.dto.CustomerDto;
import com.domainPractice.drinkOrderDomain.domain.customer.Customer;
import com.domainPractice.drinkOrderDomain.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.domain.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto registerCustomer(CustomerDto dto) {
        Customer customer = dto.toDomain();
        customerRepository.save(customer);
        return new CustomerDto(customer);
    }

    public CustomerDto getCustomer(Long id) {
        return customerRepository.findById(new CustomerId(id))
                .map(CustomerDto::new)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }
}