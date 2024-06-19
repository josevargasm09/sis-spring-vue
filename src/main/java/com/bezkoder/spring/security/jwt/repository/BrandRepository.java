package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}