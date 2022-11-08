package com.example.gestiondestock.controller;

import com.example.gestiondestock.controller.api.VenteApi;
import com.example.gestiondestock.dto.VentesDto;
import com.example.gestiondestock.services.VenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VenteController implements VenteApi {

    private VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public ResponseEntity<VentesDto> save(VentesDto ventesDto) {
        return ResponseEntity.ok(venteService.save(ventesDto));
    }

    @Override
    public ResponseEntity<VentesDto> findById(Integer id) {
        return ResponseEntity.ok(venteService.findById(id));
    }

    @Override
    public ResponseEntity<VentesDto> findByCode(String code) {
        return ResponseEntity.ok(venteService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<VentesDto>> findAll() {
        return ResponseEntity.ok(venteService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        venteService.delete(id);
        return ResponseEntity.ok().build();
    }
}
