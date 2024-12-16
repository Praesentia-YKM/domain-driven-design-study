package com.domainPractice.drinkOrderDomain.spring.presentation.payment;

import com.domainPractice.drinkOrderDomain.spring.application.payment.PaymentService;
import com.domainPractice.drinkOrderDomain.spring.application.payment.dto.PaymentDto;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.spring.domain.order.OrderId;
import com.domainPractice.drinkOrderDomain.spring.domain.order.OrderLine;
import com.domainPractice.drinkOrderDomain.spring.domain.payment.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Payment> processPayment(
            @PathVariable OrderId orderId,
            @PathVariable CustomerId customerId,
            @RequestBody PaymentDto paymentDto) {

        Payment payment = paymentDto.toEntity();
        Payment processedPayment = paymentService.processPayment(customerId, orderId, payment);
        return ResponseEntity.ok(processedPayment);
    }
}
