package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.SalesReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesReportRepository extends JpaRepository<SalesReport, Long> {
}
