package com.domainPractice.drinkOrderDomain.spring.presentation.order;

import com.domainPractice.drinkOrderDomain.spring.application.order.OrderService;
import com.domainPractice.drinkOrderDomain.spring.application.order.dto.OrderDto;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.spring.domain.order.Order;
import com.domainPractice.drinkOrderDomain.spring.domain.order.OrderLine;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.Payment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // TODO 결제와 동시에 주문이 들어가니까 표현계층은 PaymentController를 이용해야하는 거 아닐까?
//    @PostMapping
//    public OrderDto createOrder(@RequestParam CustomerId customerId,
//                                @RequestParam List<OrderLine> orderLine,
//                                @RequestParam Payment payment) {
//
//        Order resultOrder = orderService.createOrder(customerId, orderLine, payment);
//
//        return new OrderDto(resultOrder);
//    }
}