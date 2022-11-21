package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.ClientDto;
import com.example.gestiondestock.dto.UtilisateurDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.repository.UtilisateurRepository;
import com.example.gestiondestock.services.UtilisateurService;
import com.example.gestiondestock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()){
            log.error("L'utilisateur n'est pas valide {}",utilisateurDto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null){
            log.error("Utilisateur ID is null");
            return null;
        }
        return UtilisateurDto.fromEntity(utilisateurRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Aucun utilisateur avec l'id " + id + " n'a ete trouve", ErrorCodes.UTILISATEUR_NOT_FOUND))
        );
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        if (email == null){
            log.error("Utilisateur email is null");
            return null;
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.findUtilisateurByEmail(email).orElseThrow(()->
                        new EntityNotFoundException("Aucun utilisateur avec l'email fourni", ErrorCodes.UTILISATEUR_NOT_FOUND)
                )
        );
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Utilisateur ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }
}
