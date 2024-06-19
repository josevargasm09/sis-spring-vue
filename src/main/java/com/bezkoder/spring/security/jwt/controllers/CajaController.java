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

import com.bezkoder.spring.security.jwt.models.Caja;
import com.bezkoder.spring.security.jwt.payload.response.MessageResponse;
import com.bezkoder.spring.security.jwt.security.services.CajaService;

@RestController
@RequestMapping("/api/cajas")
public class CajaController {

    @Autowired
    private CajaService cajaService;

    @GetMapping
    public List<Caja> getAllCajas() {
        return cajaService.getAllCajas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCajaById(@PathVariable Long id) {
        Optional<Caja> caja = cajaService.getCajaById(id);
        if (caja.isPresent()) {
            return ResponseEntity.ok(caja.get());
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Caja no encontrada"));
        }
    }

    @PostMapping
    public ResponseEntity<?> createCaja(@RequestBody Caja caja) {
        Caja newCaja = cajaService.createCaja(caja);
        return ResponseEntity.ok(new MessageResponse("Caja creada exitosamente!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCaja(@PathVariable Long id, @RequestBody Caja cajaDetails) {
        Caja updatedCaja = cajaService.updateCaja(id, cajaDetails);
        if (updatedCaja != null) {
            return ResponseEntity.ok(new MessageResponse("Caja actualizada exitosamente!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error al actualizar la caja"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCaja(@PathVariable Long id) {
        cajaService.deleteCaja(id);
        return ResponseEntity.ok(new MessageResponse("Caja eliminada exitosamente!"));
    }
}
