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

import com.bezkoder.spring.security.jwt.models.PurchaseReport;
import com.bezkoder.spring.security.jwt.security.services.PurchaseReportService;

@RestController
@RequestMapping("/api/purchase-reports")
public class PurchaseReportController {

    @Autowired
    private PurchaseReportService purchaseReportService;

    @GetMapping
    public List<PurchaseReport> getAllReports() {
        return purchaseReportService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseReport> getReportById(@PathVariable Long id) {
        PurchaseReport report = purchaseReportService.findById(id);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public PurchaseReport createReport(@RequestBody PurchaseReport purchaseReport) {
        return purchaseReportService.save(purchaseReport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseReport> updateReport(@PathVariable Long id, @RequestBody PurchaseReport purchaseReport) {
        try {
            PurchaseReport updatedReport = purchaseReportService.update(id, purchaseReport);
            return ResponseEntity.ok(updatedReport);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        purchaseReportService.delete(id);
        return ResponseEntity.ok().build();
    }
}
