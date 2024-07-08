package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.*;
import com.bezkoder.spring.security.jwt.payload.request.SaleDTO;
import com.bezkoder.spring.security.jwt.payload.request.SaleItemDTO;
import com.bezkoder.spring.security.jwt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Autowired
    private ProductRepository productRepository;

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public Sale save(SaleDTO saleDTO) {
        Sale sale = new Sale();

        Client client = clientRepository.findById(saleDTO.getClient_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Client ID"));
        sale.setClient(client);

        Warehouse warehouse = warehouseRepository.findById(saleDTO.getWarehouse_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Warehouse ID"));
        sale.setWarehouse(warehouse);

        Invoice invoice = invoiceRepository.findById(saleDTO.getInvoice_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Invoice ID"));
        sale.setInvoice(invoice);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(saleDTO.getPayment_method_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Payment Method ID"));
        sale.setPaymentMethod(paymentMethod);

        sale.setSaleDate(saleDTO.getSale_date());
        sale.setNotes(saleDTO.getNotes());

        Set<SaleItem> saleItems = new HashSet<>();
        double totalAmount = 0;

        for (SaleItemDTO itemDTO : saleDTO.getItems()) {
            SaleItem saleItem = new SaleItem();

            Product product = productRepository.findById(itemDTO.getProduct_id())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID"));
            saleItem.setProduct(product);

            saleItem.setQuantity(itemDTO.getQuantity());
            saleItem.setPrice(itemDTO.getPrice());
            saleItem.setTotal(itemDTO.getQuantity() * itemDTO.getPrice());
            totalAmount += saleItem.getTotal();
            saleItem.setSale(sale);
            saleItems.add(saleItem);
        }

        sale.setItems(saleItems);
        sale.setTotalAmount(totalAmount);

        return saleRepository.save(sale);
    }

    public Sale update(Long id, SaleDTO saleDTO) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Sale ID"));

        Client client = clientRepository.findById(saleDTO.getClient_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Client ID"));
        sale.setClient(client);

        Warehouse warehouse = warehouseRepository.findById(saleDTO.getWarehouse_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Warehouse ID"));
        sale.setWarehouse(warehouse);

        Invoice invoice = invoiceRepository.findById(saleDTO.getInvoice_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Invoice ID"));
        sale.setInvoice(invoice);

        PaymentMethod paymentMethod = paymentMethodRepository.findById(saleDTO.getPayment_method_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Payment Method ID"));
        sale.setPaymentMethod(paymentMethod);

        sale.setSaleDate(saleDTO.getSale_date());
        sale.setNotes(saleDTO.getNotes());

        Set<SaleItem> saleItems = new HashSet<>();
        double totalAmount = 0;

        for (SaleItemDTO itemDTO : saleDTO.getItems()) {
            SaleItem saleItem = new SaleItem();

            Product product = productRepository.findById(itemDTO.getProduct_id())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID"));
            saleItem.setProduct(product);

            saleItem.setQuantity(itemDTO.getQuantity());
            saleItem.setPrice(itemDTO.getPrice());
            saleItem.setTotal(itemDTO.getQuantity() * itemDTO.getPrice());
            totalAmount += saleItem.getTotal();
            saleItem.setSale(sale);
            saleItems.add(saleItem);
        }

        sale.setItems(saleItems);
        sale.setTotalAmount(totalAmount);

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
