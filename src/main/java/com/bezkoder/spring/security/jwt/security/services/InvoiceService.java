package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.Invoice;
import com.bezkoder.spring.security.jwt.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setType(invoiceDetails.getType());
        invoice.setSeries(invoiceDetails.getSeries());
        invoice.setNumber(invoiceDetails.getNumber());
        invoice.setStatus(invoiceDetails.getStatus());
        invoice.setDate(invoiceDetails.getDate());
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
