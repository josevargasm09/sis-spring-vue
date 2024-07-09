package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.SalesReport;
import com.bezkoder.spring.security.jwt.repository.SalesReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesReportService {

    @Autowired
    private SalesReportRepository salesReportRepository;

    public List<SalesReport> findAll() {
        return salesReportRepository.findAll();
    }

    public SalesReport findById(Long id) {
        return salesReportRepository.findById(id).orElse(null);
    }

    public SalesReport save(SalesReport salesReport) {
        return salesReportRepository.save(salesReport);
    }

    public SalesReport update(Long id, SalesReport salesReport) {
        SalesReport existingReport = salesReportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Report ID"));
        existingReport.setDate(salesReport.getDate());
        existingReport.setTotalSales(salesReport.getTotalSales());
        return salesReportRepository.save(existingReport);
    }

    public void delete(Long id) {
        salesReportRepository.deleteById(id);
    }
}
