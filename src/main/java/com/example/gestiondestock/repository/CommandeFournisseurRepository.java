package com.example.gestiondestock.repository;

import com.example.gestiondestock.model.CommandeFournisseur;
import com.example.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

    Optional<CommandeFournisseur> findByCode(String code);
}
