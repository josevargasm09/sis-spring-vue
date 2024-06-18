package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.Invoice;
import com.bezkoder.spring.security.jwt.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }
}