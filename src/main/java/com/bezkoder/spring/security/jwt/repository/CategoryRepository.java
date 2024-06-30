// src/main/java/com/bezkoder/spring/security/jwt/repository/CategoryRepository.java

package com.bezkoder.spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bezkoder.spring.security.jwt.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
