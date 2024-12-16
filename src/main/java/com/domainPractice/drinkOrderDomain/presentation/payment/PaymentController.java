package com.domainPractice.drinkOrderDomain.presentation.payment;

import com.domainPractice.drinkOrderDomain.application.payment.PaymentService;
import com.domainPractice.drinkOrderDomain.application.payment.dto.PaymentDto;
import com.domainPractice.drinkOrderDomain.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.domain.order.OrderId;
import com.domainPractice.drinkOrderDomain.domain.order.OrderLine;
import com.domainPractice.drinkOrderDomain.domain.payment.Payment;
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
            @PathVariable List<OrderLine> orderLines,
            @RequestBody PaymentDto paymentDto) {

        Payment payment = paymentDto.toEntity();
        Payment processedPayment = paymentService.processPayment(customerId, orderId, payment);

        // TODO 필요한거만 보내셈 -> API 명세서도 깨짐, 따라서 컨트롤러에 Entity자체를 보내지 않는다.
        return ResponseEntity.ok(processedPayment);
    }
}
