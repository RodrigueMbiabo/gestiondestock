package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto commandeClientDto){
        List<String> errors = new ArrayList<>();
        if (commandeClientDto == null){
            errors.add("Entrer les information de la commande du client");
            return errors;
        }
        else {
            if (!StringUtils.hasLength(commandeClientDto.getCode())){
                errors.add("Entrer le code de la commande !!!");
            }
            if (commandeClientDto.getDateCommande() == null){
                errors.add("La date de la commande ne peut être null !!!");
            }
            if (commandeClientDto.getClient() == null){
                errors.add("Entrer les information du client");
            } else {
                if (StringUtils.hasLength(commandeClientDto.getClient().getNom())){
                    errors.add("Le nom du client ne peut etre vide");
                }
                if (StringUtils.hasLength(commandeClientDto.getClient().getNum_tel())){
                    errors.add("veuillez entrer le numero du client");
                }
                if (StringUtils.hasLength(commandeClientDto.getClient().getEmail())){
                    errors.add("veuillez entrer le mail du client");
                }
            }
            return errors;
        }
    }
}
