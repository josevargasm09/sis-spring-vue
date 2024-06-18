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

import com.bezkoder.spring.security.jwt.models.Invoice;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceService.findById(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody Invoice invoice) {
        invoiceService.save(invoice);
        return ResponseEntity.ok(new MessageResponse("Invoice created successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        if (invoiceService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        invoice.setId(id);
        invoiceService.save(invoice);
        return ResponseEntity.ok(new MessageResponse("Invoice updated successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
        if (invoiceService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        invoiceService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Invoice deleted successfully!"));
    }
}