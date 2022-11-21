package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.EntrepriseDto;
import com.example.gestiondestock.dto.RolesDto;
import com.example.gestiondestock.dto.UtilisateurDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.repository.EntrepriseRepository;
import com.example.gestiondestock.repository.RolesRepository;
import com.example.gestiondestock.services.EntrepriseService;
import com.example.gestiondestock.services.UtilisateurService;
import com.example.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    private final RolesRepository rolesRepository;

    private final UtilisateurService utilisateurService;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, RolesRepository rolesRepository, UtilisateurService utilisateurService) {
        this.entrepriseRepository = entrepriseRepository;
        this.rolesRepository = rolesRepository;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validateEntreprise(entrepriseDto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto))
        );

        UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

        UtilisateurDto savedUser = utilisateurService.save(utilisateur);

        RolesDto rolesDto = RolesDto.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        rolesRepository.save(RolesDto.toEntity(rolesDto));

        return  savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder()
                .adresse(dto.getAdresse())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .motDePasse(generateRandomPassword())
                .entreprise(dto)
                .dateNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null){
            log.error("Entreprise ID is null");
            return null;
        }
        return EntrepriseDto.fromEntity(entrepriseRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Aucune entreprise avec l'id " + id + " n'a ete trouve", ErrorCodes.ENTREPRISE_NOT_FOUND))
        );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
