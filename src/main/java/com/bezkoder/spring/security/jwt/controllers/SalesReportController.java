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

import com.bezkoder.spring.security.jwt.models.SalesReport;
import com.bezkoder.spring.security.jwt.security.services.SalesReportService;

@RestController
@RequestMapping("/api/reports")
public class SalesReportController {

    @Autowired
    private SalesReportService salesReportService;

    @GetMapping
    public List<SalesReport> getAllReports() {
        return salesReportService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesReport> getReportById(@PathVariable Long id) {
        SalesReport report = salesReportService.findById(id);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public SalesReport createReport(@RequestBody SalesReport salesReport) {
        return salesReportService.save(salesReport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesReport> updateReport(@PathVariable Long id, @RequestBody SalesReport salesReport) {
        try {
            SalesReport updatedReport = salesReportService.update(id, salesReport);
            return ResponseEntity.ok(updatedReport);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        salesReportService.delete(id);
        return ResponseEntity.ok().build();
    }
}
