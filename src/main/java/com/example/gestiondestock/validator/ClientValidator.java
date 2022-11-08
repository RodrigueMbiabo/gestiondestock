package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.ClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validateClient(ClientDto clientDto){
        List<String> error = new ArrayList<>();

        if (clientDto == null){
            error.add("Le nom du client ne peut etre vide");
            error.add("veuillez entrer le numero du client");
            error.add("veuillez entrer le mail du client");
            return error;
        }
        else {
            if (StringUtils.hasLength(clientDto.getNom())){
                error.add("Le nom du client ne peut etre vide");
            }
            if (StringUtils.hasLength(clientDto.getNum_tel())){
                error.add("veuillez entrer le numero du client");
            }
            if (StringUtils.hasLength(clientDto.getEmail())){
                error.add("veuillez entrer le mail du client");
            }
        }

        return error;
    }
}
