package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.ClientReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientReportRepository extends JpaRepository<ClientReport, Long> {
}
