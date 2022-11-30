package com.example.gestiondestock.repository;

import com.example.gestiondestock.model.LigneVente;
import com.example.gestiondestock.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

    Optional<Ventes> findByCode(String code);
}
