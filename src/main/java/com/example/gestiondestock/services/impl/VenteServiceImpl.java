package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.LigneVenteDto;
import com.example.gestiondestock.dto.VentesDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.LigneVente;
import com.example.gestiondestock.model.Ventes;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.LigneVenteRepository;
import com.example.gestiondestock.repository.VentesRepository;
import com.example.gestiondestock.services.VenteService;
import com.example.gestiondestock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private final ArticleRepository articleRepository;
    private final VentesRepository ventesRepository;
    private final LigneVenteRepository ligneVenteRepository;

    public VenteServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {
        List<String> errors = VenteValidator.validateVente(ventesDto);

        if (!errors.isEmpty()){
            log.error("Ventes n'est pas valide");
            throw new InvalidEntityException("", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        ventesDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (!article.isPresent()){
                articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + "n'a ete trouve dans la BDD");
            }
        });

        if (!articleErrors.isEmpty()){
            log.error("One or more articles were not found in the DB, {}", errors);
            throw new InvalidEntityException("un ou plusieurs articles n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Ventes ventes = ventesRepository.save(VentesDto.toEntity(ventesDto));

        ventesDto.getLigneVenteDtos().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(ventes);
            ligneVenteRepository.save(ligneVente);
        });
        return VentesDto.fromEntity(ventes);
    }

    @Override
    public VentesDto findById(Integer id) {
        if (id == null){
            log.error("Ventes ID is NULL");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente n'a ete trouve avec l'id" + id, ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Ventes CODE is NULL");
            return null;
        }
        return ventesRepository.findByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente n'a ete trouve avec le" + code, ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Ventes ID is NULL");
            return;
        }
        ventesRepository.deleteById(id);
    }
}
