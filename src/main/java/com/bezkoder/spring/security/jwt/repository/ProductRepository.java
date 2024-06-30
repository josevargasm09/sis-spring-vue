// src/main/java/com/bezkoder/spring/security/jwt/repository/ProductRepository.java

package com.bezkoder.spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bezkoder.spring.security.jwt.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
