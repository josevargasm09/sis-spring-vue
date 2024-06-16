package com.bezkoder.spring.security.jwt.security.services;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bezkoder.spring.security.jwt.models.Role;
import com.bezkoder.spring.security.jwt.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

public class RoleService {
     @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role roleDetails) {
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (roleOpt.isPresent()) {
            Role role = roleOpt.get();
            role.setName(roleDetails.getName());
            return roleRepository.save(role);
        } else {
            throw new ResourceNotFoundException("Role not found for this id :: " + id);
        }
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}
