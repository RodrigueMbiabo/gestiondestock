package com.example.gestiondestock.services;

import com.example.gestiondestock.dto.VentesDto;

import java.util.List;

public interface VenteService {

    VentesDto save(VentesDto ventesDto);

    VentesDto findById(Integer id);

    VentesDto findByCode(String code);

    List<VentesDto> findAll();

    void delete(Integer id);
}
