package com.example.gestiondestock.repository;

import com.example.gestiondestock.model.Client;
import com.example.gestiondestock.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {

    Optional<CommandeClient> findByCode(String code);
}
