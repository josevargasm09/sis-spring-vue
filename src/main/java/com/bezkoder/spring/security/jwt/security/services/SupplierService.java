package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.Supplier;
import com.bezkoder.spring.security.jwt.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplier.setName(supplierDetails.getName());
        supplier.setAddress(supplierDetails.getAddress());
        supplier.setPhone(supplierDetails.getPhone());
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplierRepository.delete(supplier);
    }
}
