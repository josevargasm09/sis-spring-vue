package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Sale;
import com.bezkoder.spring.security.jwt.repository.SaleRepository;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

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
        Optional<Sale> optionalSale = saleRepository.findById(id);
        if (optionalSale.isPresent()) {
            Sale sale = optionalSale.get();
            sale.setClient(saleDetails.getClient());
            sale.setSaleDate(saleDetails.getSaleDate());
            sale.setInvoice(saleDetails.getInvoice());
            sale.setPaymentMethod(saleDetails.getPaymentMethod());
            sale.setTotalAmount(saleDetails.getTotalAmount());
            sale.setItems(saleDetails.getItems());
            return saleRepository.save(sale);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}