package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.VentesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.example.gestiondestock.utils.Constants.APP_ROOT;

public interface VenteApi {

    @PostMapping(APP_ROOT + "/ventes/create")
    ResponseEntity<VentesDto> save(VentesDto ventesDto);

    @GetMapping(APP_ROOT + "/ventes/{id}")
    ResponseEntity<VentesDto> findById(Integer id);

    @GetMapping(APP_ROOT + "/ventes/{code}")
    ResponseEntity<VentesDto> findByCode(String code);

    @GetMapping(APP_ROOT + "/ventes/all")
    ResponseEntity<List<VentesDto>> findAll();

    @DeleteMapping(APP_ROOT + "/ventes/delete/{id}")
    ResponseEntity delete(Integer id);
}
