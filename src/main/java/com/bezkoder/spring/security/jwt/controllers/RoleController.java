// src/main/java/com/bezkoder/spring/security/jwt/controllers/RoleController.java
package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.models.Role;
import com.bezkoder.spring.security.jwt.payload.request.RoleRequest;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/")
    public ResponseEntity<?> createRole(@RequestBody RoleRequest roleRequest) {
        Role role = new Role(roleRequest.getName());
        roleRepository.save(role);
        return ResponseEntity.ok(new MessageResponse("Role created successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody RoleRequest roleRequest) {
        Optional<Role> roleOpt = roleRepository.findById(id);

        if (roleOpt.isPresent()) {
            Role role = roleOpt.get();
            role.setName(roleRequest.getName());
            roleRepository.save(role);
            return ResponseEntity.ok(new MessageResponse("Role updated successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Role not found!"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Role deleted successfully!"));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleRepository.findAll());
    }
}
