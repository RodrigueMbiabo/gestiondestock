package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.CommandeFournisseurDto;
import com.example.gestiondestock.dto.FournisseurDto;
import com.example.gestiondestock.model.CommandeFournisseur;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validateFournisseur(CommandeFournisseurDto commandeFournisseurDto){
        List<String> errors = new ArrayList<>();

        if (commandeFournisseurDto == null){
            errors.add("Entrer les information de la commande du fournisseur");
            return errors;
        }
        else {
            if (!StringUtils.hasLength(commandeFournisseurDto.getCode())){
                errors.add("Entrer le code de la commande !!!");
            }
            if (commandeFournisseurDto.getDateCommande() == null){
                errors.add("La date de la commande ne peut être null !!!");
            }
            if (commandeFournisseurDto.getFournisseur() == null){
                errors.add("Entrer les information du fournisseur");
            } else {
                if (StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getNom())){
                    errors.add("Le nom du fournisseur ne peut etre vide");
                }
                if (StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getNum_tel())){
                    errors.add("veuillez entrer le numero du fournisseur");
                }
                if (StringUtils.hasLength(commandeFournisseurDto.getFournisseur().getEmail())){
                    errors.add("veuillez entrer le mail du fourisseur");
                }
            }
            return errors;
        }
    }
}
