package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {
    public static List<String> validateFournisseur(FournisseurDto fournisseurDto){
        List<String> error = new ArrayList<>();

        if (fournisseurDto == null){
            error.add("Le nom du fournisseur ne peut etre vide");
            error.add("veuillez entrer le numero du fournisseur");
            error.add("veuillez entrer le mail du fournisseur");
            return error;
        }
        else {
            if (StringUtils.hasLength(fournisseurDto.getNom())){
                error.add("Le nom du fournisseur ne peut etre vide");
            }
            if (StringUtils.hasLength(fournisseurDto.getNum_tel())){
                error.add("veuillez entrer le numero du fournisseur");
            }
            if (StringUtils.hasLength(fournisseurDto.getEmail())){
                error.add("veuillez entrer le mail du fournisseur");
            }
        }

        return error;
    }
}
