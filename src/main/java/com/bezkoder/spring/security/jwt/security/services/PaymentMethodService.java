package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.PaymentMethod;
import com.bezkoder.spring.security.jwt.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethod findById(Long id) {
        return paymentMethodRepository.findById(id).orElse(null);
    }

    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    public void delete(Long id) {
        paymentMethodRepository.deleteById(id);
    }
}