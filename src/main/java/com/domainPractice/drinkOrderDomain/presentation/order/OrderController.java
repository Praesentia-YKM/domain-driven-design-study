package com.domainPractice.drinkOrderDomain.presentation.order;

import com.domainPractice.drinkOrderDomain.application.order.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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