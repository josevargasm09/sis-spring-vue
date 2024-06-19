package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.security.jwt.models.Brand;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.BrandService;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id) {
        Optional<Brand> brand = brandService.getBrandById(id);
        if (brand.isPresent()) {
            return ResponseEntity.ok(brand.get());
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Marca no encontrada"));
        }
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand savedBrand = brandService.saveBrand(brand);
        return ResponseEntity.ok(savedBrand);
    }

    
    @PostMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Long id, @RequestBody Brand brand) {
    Optional<Brand> existingBrand = brandService.getBrandById(id);
    if (existingBrand.isPresent()) {
        Brand updatedBrand = existingBrand.get();
        updatedBrand.setName(brand.getName());
        Brand savedBrand = brandService.saveBrand(updatedBrand);
        return ResponseEntity.ok(savedBrand);
    } else {
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Marca no encontrada"));
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.ok(new MessageResponse("Marca eliminada con éxito"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error al eliminar la marca"));
        }
    }
}