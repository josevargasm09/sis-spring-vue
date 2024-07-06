package com.bezkoder.spring.security.jwt.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.security.jwt.models.Purchase;
import com.bezkoder.spring.security.jwt.payload.request.PurchaseDTO;
import com.bezkoder.spring.security.jwt.repository.PurchaseRepository;
import com.bezkoder.spring.security.jwt.repository.SupplierRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<PurchaseDTO> getAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return purchases.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PurchaseDTO getPurchaseById(Long id) {
        Purchase purchase = purchaseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase not found"));
        return convertToDTO(purchase);
    }

    public Purchase createPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = convertToEntity(purchaseDTO);
        return purchaseRepository.save(purchase);
    }

    public PurchaseDTO updatePurchase(Long id, PurchaseDTO purchaseDTO) {
        Purchase existingPurchase = purchaseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase not found"));

        existingPurchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
        existingPurchase.setTotalAmount(purchaseDTO.getTotalAmount());

        // Actualizar proveedor
        if (purchaseDTO.getSupplierId() != null) {
            existingPurchase.setSupplier(supplierRepository.findById(purchaseDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found")));
        }

        Purchase updatedPurchase = purchaseRepository.save(existingPurchase);
        return convertToDTO(updatedPurchase);
    }

    public void deletePurchase(Long id) {
        if (!purchaseRepository.existsById(id)) {
            throw new RuntimeException("Purchase not found");
        }
        purchaseRepository.deleteById(id);
    }

    private PurchaseDTO convertToDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(purchase.getId());
        purchaseDTO.setPurchaseDate(purchase.getPurchaseDate());
        purchaseDTO.setTotalAmount(purchase.getTotalAmount());
        if (purchase.getSupplier() != null) {
            purchaseDTO.setSupplierId(purchase.getSupplier().getId());
        }
        return purchaseDTO;
    }

    private Purchase convertToEntity(PurchaseDTO purchaseDTO) {
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
        purchase.setTotalAmount(purchaseDTO.getTotalAmount());

        // Obtener y asignar el proveedor
        if (purchaseDTO.getSupplierId() != null) {
            purchase.setSupplier(supplierRepository.findById(purchaseDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found")));
        }

        return purchase;
    }
}
