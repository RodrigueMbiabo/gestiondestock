package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.FournisseurDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.repository.FournisseurRepository;
import com.example.gestiondestock.services.FournisseurService;
import com.example.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validateFournisseur(fournisseurDto);
        if (!errors.isEmpty()){
            log.error("Le fournisseur n'est pas valide {}",fournisseurDto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {

        if (id == null){
            log.error("Fournisseur ID is null");
            return null;
        }
        return FournisseurDto.fromEntity(fournisseurRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Aucun fournisseur avec l'id " + id + " n'a ete trouve", ErrorCodes.FOURNISSEUR_NOT_FOUND))
        );
    }

    @Override
    public List<FournisseurDto> findAll() {

        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Fournisseur ID is null");
            return;
        }
        fournisseurRepository.deleteById(id);
    }
}
