package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto){
        List<String> errors = new ArrayList<>();

        if (articleDto == null){
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("veuillez renseigner la désignatton");
            errors.add("veuillez renseigner le prix unitaire hors taxe");
            errors.add("veuillez renseigner le taux Tva");
            errors.add("veuillez renseigner le prix unitaire Ttc");
            errors.add("veuillez selectionner une categorie");
            return errors;
        }

        if (!StringUtils.hasLength(articleDto.getCodeArticle())){
            errors.add("Veuillez renseigner le code de l'article");
        }

        if (!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("veuillez renseigner la désignatton");
        }

        if (articleDto.getPrixUnitaireHt() == null){
            errors.add("veuillez renseigner le prix unitaire hors taxe");
        }

        if (articleDto.getTauxTva() == null){
            errors.add("veuillez renseigner le taux Tva");
        }

        if (articleDto.getPrixUnitaireTtc() == null){
            errors.add("veuillez renseigner le prix unitaire Ttc");
        }

        /*if (articleDto.getCategory() == null){
            errors.add("veuillez selectionner une categorie");
        }*/

        return errors;
    }
}
