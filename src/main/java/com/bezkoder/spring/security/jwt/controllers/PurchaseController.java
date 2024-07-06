package com.bezkoder.spring.security.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.security.jwt.models.Purchase;
import com.bezkoder.spring.security.jwt.payload.request.PurchaseDTO;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases() {
        List<PurchaseDTO> purchases = purchaseService.getAllPurchases();
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseDTO> getPurchaseById(@PathVariable Long id) {
        PurchaseDTO purchase = purchaseService.getPurchaseById(id);
        return ResponseEntity.ok(purchase);
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseService.createPurchase(purchaseDTO);
        return ResponseEntity.ok(purchase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePurchase(@PathVariable Long id, @RequestBody PurchaseDTO purchaseDTO) {
        PurchaseDTO updatedPurchase = purchaseService.updatePurchase(id, purchaseDTO);
        return ResponseEntity.ok(new MessageResponse("Purchase updated successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.ok(new MessageResponse("Purchase deleted successfully!"));
    }
}
