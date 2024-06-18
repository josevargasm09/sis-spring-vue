package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}