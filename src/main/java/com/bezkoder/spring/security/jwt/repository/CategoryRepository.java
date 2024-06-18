// src/main/java/com/bezkoder/spring/security/jwt/repository/CategoryRepository.java

package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Custom query methods can be defined here if needed
}
