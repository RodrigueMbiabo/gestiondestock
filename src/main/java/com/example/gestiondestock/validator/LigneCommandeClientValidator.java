package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto){
        List<String> error = new ArrayList<>();

        if (ligneCommandeClientDto == null){
            error.add("Veuillez entrer les informations de la ligne commande client");
            return error;
        }
        else {
            if (ligneCommandeClientDto.getArticle() == null){
                error.add("l'article n'est pas valide ");
            }
            return error;
        }
    }
}
