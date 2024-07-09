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

import com.bezkoder.spring.security.jwt.models.ClientReport;
import com.bezkoder.spring.security.jwt.security.services.ClientReportService;

@RestController
@RequestMapping("/api/client-reports")
public class ClientReportController {

    @Autowired
    private ClientReportService clientReportService;

    @GetMapping
    public List<ClientReport> getAllReports() {
        return clientReportService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientReport> getReportById(@PathVariable Long id) {
        ClientReport report = clientReportService.findById(id);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ClientReport createReport(@RequestBody ClientReport clientReport) {
        return clientReportService.save(clientReport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientReport> updateReport(@PathVariable Long id, @RequestBody ClientReport clientReport) {
        try {
            ClientReport updatedReport = clientReportService.update(id, clientReport);
            return ResponseEntity.ok(updatedReport);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        clientReportService.delete(id);
        return ResponseEntity.ok().build();
    }
}
