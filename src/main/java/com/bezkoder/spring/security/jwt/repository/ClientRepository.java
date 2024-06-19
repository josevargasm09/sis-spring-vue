// src/main/java/com/bezkoder/spring/security/jwt/repositories/ClientRepository.java

package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNameContainingOrDniContaining(String name, String dni);
}
