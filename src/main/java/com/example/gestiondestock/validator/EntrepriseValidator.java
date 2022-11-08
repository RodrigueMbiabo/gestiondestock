package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validateEntreprise(EntrepriseDto entrepriseDto){
        List<String> error = new ArrayList<>();

        if (entrepriseDto == null){
            error.add("Le nom de l'entreprise ne peut etre vide");
            error.add("veuillez entrer le code Fiscal de l'entreprise");
            error.add("veuillez entrer le mail de l'entreprise");
            return error;
        }
        else {
            if (StringUtils.hasLength(entrepriseDto.getNom())){
                error.add("Le nom de l'entreprise ne peut etre vide");
            }
            if (StringUtils.hasLength(entrepriseDto.getCodeFiscal())){
                error.add("veuillez entrer le code Fiscal de l'entreprise");
            }
            if (StringUtils.hasLength(entrepriseDto.getEmail())){
                error.add("veuillez entrer le mail de l'entreprise");
            }
        }

        return error;
    }
}
