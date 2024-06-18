package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.Branch;
import com.bezkoder.spring.security.jwt.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Optional<Branch> getBranchById(Long id) {
        return branchRepository.findById(id);
    }

    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    public Branch updateBranch(Long id, Branch branchDetails) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch.get();
            branch.setName(branchDetails.getName());
            branch.setAddress(branchDetails.getAddress());
            branch.setPhone(branchDetails.getPhone());
            return branchRepository.save(branch);
        } else {
            return null;
        }
    }

    public void deleteBranch(Long id) {
        branchRepository.deleteById(id);
    }
}
