package com.domainPractice.drinkOrderDomain.spring.infrastructure.persistence.order;

import com.domainPractice.drinkOrderDomain.spring.domain.order.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long>, OrderRepository {
    @Override
    Optional<Order> findOrderById(OrderId orderId);
    
    @Query("SELECT ol FROM OrderLine ol WHERE ol.order.id = :orderId")
    List<OrderLine> findOrderLinesByOrderId(OrderId orderId);
}
