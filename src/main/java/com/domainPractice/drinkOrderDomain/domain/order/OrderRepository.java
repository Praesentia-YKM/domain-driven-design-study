package com.domainPractice.drinkOrderDomain.domain.order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findOrderById(OrderId orderId);
    List<OrderLine> findOrderLinesByOrderId(OrderId orderId);
}