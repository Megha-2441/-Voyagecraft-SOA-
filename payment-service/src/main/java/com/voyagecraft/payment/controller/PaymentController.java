package com.voyagecraft.payment.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping
    public Map<String, Object> processPayment(
            @RequestBody Map<String, Object> payment) {

        return Map.of(
                "message", "Payment processed successfully",
                "transactionId", "TXN1001",
                "status", "SUCCESS",
                "payment", payment
        );
    }
}