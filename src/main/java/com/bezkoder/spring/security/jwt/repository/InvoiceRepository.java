package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}