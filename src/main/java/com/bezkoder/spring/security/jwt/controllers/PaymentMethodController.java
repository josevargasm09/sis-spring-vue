package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.jwt.models.PaymentMethod;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.PaymentMethodService;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethodById(@PathVariable Long id) {
        PaymentMethod paymentMethod = paymentMethodService.findById(id);
        if (paymentMethod == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentMethod);
    }

    @PostMapping
    public ResponseEntity<?> createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        paymentMethodService.save(paymentMethod);
        return ResponseEntity.ok(new MessageResponse("Payment Method created successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethod paymentMethod) {
        if (paymentMethodService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        paymentMethod.setId(id);
        paymentMethodService.save(paymentMethod);
        return ResponseEntity.ok(new MessageResponse("Payment Method updated successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable Long id) {
        if (paymentMethodService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        paymentMethodService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Payment Method deleted successfully!"));
    }
}