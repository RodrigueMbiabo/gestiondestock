package com.example.gestiondestock.controller;

import com.example.gestiondestock.controller.api.CommandeClientApi;
import com.example.gestiondestock.dto.CommandeClientDto;
import com.example.gestiondestock.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi{

    private CommandeClientService commandeClientService;

    @Autowired
    public  CommandeClientController(CommandeClientService commandeClientService){
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto dto) {
        return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer idCommandeClient) {
        return ResponseEntity.ok(commandeClientService.findById(idCommandeClient));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String codeCommandeClient) {
        return ResponseEntity.ok(commandeClientService.findByCode(codeCommandeClient));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer idCommandeClient) {
        commandeClientService.delete(idCommandeClient);
        return ResponseEntity.ok().build();
    }
}
