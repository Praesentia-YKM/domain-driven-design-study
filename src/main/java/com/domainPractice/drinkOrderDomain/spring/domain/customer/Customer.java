package com.domainPractice.drinkOrderDomain.spring.domain.customer;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Customer {

    @EmbeddedId
    private CustomerId id;

    private String name;

    private String phoneNumber;

    public Customer(CustomerId id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}