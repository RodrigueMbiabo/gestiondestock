package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.gestiondestock.utils.Constants.APP_ROOT;

public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateur/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateur/getUtilisateurById/{idUtilisateur}", produces =  MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = APP_ROOT + "/utilisateur/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateur/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}
