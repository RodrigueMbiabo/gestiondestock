package com.example.gestiondestock.validator;

import com.example.gestiondestock.dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validateVente(VentesDto ventesDto){
        List<String> errors = new ArrayList<>();

        if (ventesDto == null){
            errors.add("La validation ne peut etre null");
            return errors;
        }
        else {
            if (!StringUtils.hasLength(ventesDto.getCode())){
                errors.add("Veuillez renseigner le code de la vente");
            }
            return errors;
        }
    }
}
