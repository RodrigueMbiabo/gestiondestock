package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.gestiondestock.utils.Constants.APP_ROOT;

public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + " /entreprise/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto);

    @GetMapping(value = APP_ROOT + "/entreprise/getEntrepriseById/{idEntreprise}", produces =  MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = APP_ROOT + "/entreprise/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprise/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Integer id);
}
