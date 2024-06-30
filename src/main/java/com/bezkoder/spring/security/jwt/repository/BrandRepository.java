// src/main/java/com/bezkoder/spring/security/jwt/repository/BrandRepository.java

package com.bezkoder.spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bezkoder.spring.security.jwt.models.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
