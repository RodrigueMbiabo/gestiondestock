package com.example.gestiondestock.controller;

import com.example.gestiondestock.controller.api.CommandeFournisseurApi;
import com.example.gestiondestock.dto.CommandeFournisseurDto;
import com.example.gestiondestock.services.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> save(CommandeFournisseurDto dto) {
        return ResponseEntity.ok(commandeFournisseurService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findById(Integer idCommandeFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.findById(idCommandeFournisseur));
    }

    @Override
    public ResponseEntity<CommandeFournisseurDto> findByCode(String codeCommandeFournisseur) {
        return ResponseEntity.ok(commandeFournisseurService.findByCode(codeCommandeFournisseur));
    }

    @Override
    public ResponseEntity<List<CommandeFournisseurDto>> findAll() {
        return ResponseEntity.ok(commandeFournisseurService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer idCommandeFournisseur) {
        commandeFournisseurService.delete(idCommandeFournisseur);
        return ResponseEntity.ok().build();
    }
}
