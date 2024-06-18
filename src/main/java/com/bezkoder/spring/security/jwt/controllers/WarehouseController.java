package com.bezkoder.spring.security.jwt.controllers;

import java.util.List;

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

import com.bezkoder.spring.security.jwt.models.Warehouse;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.WarehouseService;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        Warehouse warehouse = warehouseService.findById(id);
        if (warehouse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(warehouse);
    }

    @PostMapping
    public ResponseEntity<?> createWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.save(warehouse);
        return ResponseEntity.ok(new MessageResponse("Warehouse created successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse) {
        if (warehouseService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        warehouse.setId(id);
        warehouseService.save(warehouse);
        return ResponseEntity.ok(new MessageResponse("Warehouse updated successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable Long id) {
        if (warehouseService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        warehouseService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Warehouse deleted successfully!"));
    }
}