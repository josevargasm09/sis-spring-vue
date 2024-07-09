package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.PurchaseReport;
import com.bezkoder.spring.security.jwt.repository.PurchaseReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseReportService {

    @Autowired
    private PurchaseReportRepository purchaseReportRepository;

    public List<PurchaseReport> findAll() {
        return purchaseReportRepository.findAll();
    }

    public PurchaseReport findById(Long id) {
        return purchaseReportRepository.findById(id).orElse(null);
    }

    public PurchaseReport save(PurchaseReport purchaseReport) {
        return purchaseReportRepository.save(purchaseReport);
    }

    public PurchaseReport update(Long id, PurchaseReport purchaseReport) {
        PurchaseReport existingReport = purchaseReportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Report ID"));
        existingReport.setDate(purchaseReport.getDate());
        existingReport.setTotalPurchases(purchaseReport.getTotalPurchases());
        return purchaseReportRepository.save(existingReport);
    }

    public void delete(Long id) {
        purchaseReportRepository.deleteById(id);
    }
}
