// src/main/java/com/bezkoder/spring/security/jwt/repository/PurchaseRepository.java
package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
