package com.bezkoder.spring.security.jwt.controllers;

import com.bezkoder.spring.security.jwt.models.Role;
import com.bezkoder.spring.security.jwt.models.User;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.repository.RoleRepository;
import com.bezkoder.spring.security.jwt.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            Role userRole = roleRepository.findById(role.getId())
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        user.setRoles(roles);

        userService.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword()); // Ideally, hash the password

            Set<Role> roles = new HashSet<>();
            for (Role role : userDetails.getRoles()) {
                Role userRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            }
            user.setRoles(roles);

            User updatedUser = userService.saveUser(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            userService.deleteUserById(id);
            return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
