package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}