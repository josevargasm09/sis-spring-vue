package com.bezkoder.spring.security.jwt.security.services;

import com.bezkoder.spring.security.jwt.models.Caja;
import com.bezkoder.spring.security.jwt.repository.CajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CajaService {

    @Autowired
    private CajaRepository cajaRepository;

    public List<Caja> getAllCajas() {
        return cajaRepository.findAll();
    }

    public Optional<Caja> getCajaById(Long id) {
        return cajaRepository.findById(id);
    }

    public Caja createCaja(Caja caja) {
        return cajaRepository.save(caja);
    }

    public Caja updateCaja(Long id, Caja cajaDetails) {
        Optional<Caja> optionalCaja = cajaRepository.findById(id);
        if (optionalCaja.isPresent()) {
            Caja caja = optionalCaja.get();
            caja.setSerie(cajaDetails.getSerie());
            caja.setTipoCaja(cajaDetails.getTipoCaja());
            caja.setOficina(cajaDetails.getOficina());
            caja.setDescripcion(cajaDetails.getDescripcion());
            caja.setActivo(cajaDetails.isActivo());
            return cajaRepository.save(caja);
        }
        return null; // Puedes lanzar una excepción personalizada si es necesario
    }

    public void deleteCaja(Long id) {
        cajaRepository.deleteById(id);
    }
}
