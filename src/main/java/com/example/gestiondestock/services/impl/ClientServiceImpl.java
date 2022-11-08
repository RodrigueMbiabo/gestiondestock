package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.ClientDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.repository.ClientRepository;
import com.example.gestiondestock.services.ClientService;
import com.example.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validateClient(clientDto);
        if (!errors.isEmpty()){
            log.error("Le client n'est pas valide {}",clientDto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null){
            log.error("Client ID is null");
            return null;
        }
        return ClientDto.fromEntity(clientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Aucun client avec l'id " + id + " n'a ete trouve", ErrorCodes.CLIENT_NOT_FOUND))
        );
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Client ID is null");
            return;
        }
        clientRepository.deleteById(id);
    }
}
