package com.bezkoder.spring.security.jwt.repository;

import com.bezkoder.spring.security.jwt.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    
}
