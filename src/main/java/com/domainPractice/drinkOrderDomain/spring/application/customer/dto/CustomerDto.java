package com.domainPractice.drinkOrderDomain.spring.application.customer.dto;

import com.domainPractice.drinkOrderDomain.spring.domain.customer.Customer;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerId;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String phoneNumber;

    public CustomerDto(Customer customer) {
        this.id = customer.getId().getValue();
        this.name = customer.getName();
        this.phoneNumber = customer.getPhoneNumber();
    }

    public Customer toDomain() {
        return new Customer(new CustomerId(this.id), this.name, this.phoneNumber);
    }
}