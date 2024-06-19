package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}
