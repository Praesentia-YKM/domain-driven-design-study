package com.domainPractice.drinkOrderDomain.application.order.dto;

import com.domainPractice.drinkOrderDomain.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long customerId;

    public OrderDto(Order order) {
        this.id = order.getId().getValue();
        this.customerId = order.getCustomerId().getValue();
    }
}
