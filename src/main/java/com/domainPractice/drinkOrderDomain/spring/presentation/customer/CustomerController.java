package com.domainPractice.drinkOrderDomain.spring.presentation.customer;

import com.domainPractice.drinkOrderDomain.spring.application.customer.CustomerService;
import com.domainPractice.drinkOrderDomain.spring.application.customer.dto.CustomerDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDto registerCustomer(@RequestBody CustomerDto dto) {
        return customerService.registerCustomer(dto);
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }
}
