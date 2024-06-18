package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}