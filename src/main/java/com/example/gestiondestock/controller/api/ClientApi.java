package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.ClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.example.gestiondestock.utils.Constants.APP_ROOT;

public interface ClientApi {

    @PostMapping(value = APP_ROOT + " client/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value = APP_ROOT + "client/getClientById/{idClient}", produces =  MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "client/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<ClientDto> findAll();

    @GetMapping(value = APP_ROOT + "client/delete/{idClient}")
    void delete(@PathVariable("idClient") Integer id);
}
