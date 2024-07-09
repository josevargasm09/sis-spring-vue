package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.ClientReport;
import com.bezkoder.spring.security.jwt.repository.ClientReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientReportService {

    @Autowired
    private ClientReportRepository clientReportRepository;

    public List<ClientReport> findAll() {
        return clientReportRepository.findAll();
    }

    public ClientReport findById(Long id) {
        return clientReportRepository.findById(id).orElse(null);
    }

    public ClientReport save(ClientReport clientReport) {
        return clientReportRepository.save(clientReport);
    }

    public ClientReport update(Long id, ClientReport clientReport) {
        ClientReport existingReport = clientReportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Report ID"));
        existingReport.setReportDate(clientReport.getReportDate());
        existingReport.setTotalClients(clientReport.getTotalClients());
        return clientReportRepository.save(existingReport);
    }

    public void delete(Long id) {
        clientReportRepository.deleteById(id);
    }
}
