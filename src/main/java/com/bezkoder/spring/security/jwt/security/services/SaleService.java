package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Sale;
import com.bezkoder.spring.security.jwt.repository.ClientRepository;
import com.bezkoder.spring.security.jwt.repository.InvoiceRepository;
import com.bezkoder.spring.security.jwt.repository.PaymentMethodRepository;
import com.bezkoder.spring.security.jwt.repository.SaleRepository;
import com.bezkoder.spring.security.jwt.repository.WarehouseRepository;

@Service
public class SaleService {

        @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

        public Sale findById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

   public Sale update(Long id, Sale saleDetails) {
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
        sale.setClient(clientRepository.findById(saleDetails.getClient().getId()).orElseThrow(() -> new RuntimeException("Client not found")));
        sale.setWarehouse(warehouseRepository.findById(saleDetails.getWarehouse().getId()).orElseThrow(() -> new RuntimeException("Warehouse not found")));
        sale.setInvoice(invoiceRepository.findById(saleDetails.getInvoice().getId()).orElseThrow(() -> new RuntimeException("Invoice not found")));
        sale.setPaymentMethod(paymentMethodRepository.findById(saleDetails.getPaymentMethod().getId()).orElseThrow(() -> new RuntimeException("Payment method not found")));
        sale.setSaleDate(saleDetails.getSaleDate());
        sale.setTotalAmount(saleDetails.getTotalAmount());
        sale.setItems(saleDetails.getItems());
        return saleRepository.save(sale);
    }

    public boolean delete(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}