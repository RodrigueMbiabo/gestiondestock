package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.CommandeFournisseurDto;
import com.example.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.model.Article;
import com.example.gestiondestock.model.CommandeFournisseur;
import com.example.gestiondestock.model.Fournisseur;
import com.example.gestiondestock.model.LigneCommandeFournisseur;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.repository.CommandeFournisseurRepository;
import com.example.gestiondestock.repository.FournisseurRepository;
import com.example.gestiondestock.repository.LigneCommandeFournisseurRepository;
import com.example.gestiondestock.services.CommandeFournisseurService;
import com.example.gestiondestock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final CommandeFournisseurRepository commandeFournisseurRepository;
    private final FournisseurRepository fournisseurRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private final ArticleRepository articleRepository;

    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, FournisseurRepository fournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, ArticleRepository articleRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = CommandeFournisseurValidator.validateFournisseur(commandeFournisseurDto);
        if (!errors.isEmpty()){
            log.error("Commande fournisseur n'est pas valide");
            throw new InvalidEntityException("La commande du fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if (!fournisseur.isPresent()){
            log.warn("Fournisseur with ID {} was not found in the DB", commandeFournisseurDto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID" + commandeFournisseurDto.getFournisseur().getId() + " n'a etet trouve dans la BDD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null){
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligneCommandeFournisseurDto -> {
                if (ligneCommandeFournisseurDto.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligneCommandeFournisseurDto.getArticle().getId());
                    if (!article.isPresent()){
                        articleErrors.add("L'article avec l'id " + ligneCommandeFournisseurDto.getArticle().getId() + " n'existe pas");
                    }
                } else {
                    articleErrors.add("impossible d'enregistrer un article NULL ");
                }
            });
        }

        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur commandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligneCommandeFournisseurDto ->{
            LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto);
            ligneCommandeFournisseur.setCommandeFournisseur(commandeFournisseur);
        });
        return CommandeFournisseurDto.fromEntity(commandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id == null){
            log.warn("Commande fournisseur ID is NULL");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (code == null){
            log.warn("Commande fournissseur Code is NULL");
            return null;
        }
        return commandeFournisseurRepository.findByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournissseur n'a ete trouve avec le code" + code, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.warn("Commande fournissseur ID is NULL");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }

}
