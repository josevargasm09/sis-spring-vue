package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.PurchaseReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseReportRepository extends JpaRepository<PurchaseReport, Long> {
}
