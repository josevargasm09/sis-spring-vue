package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
