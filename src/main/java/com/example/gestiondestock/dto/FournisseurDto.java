package com.example.gestiondestock.dto;

import com.example.gestiondestock.model.Entreprise;
import com.example.gestiondestock.model.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    @JsonIgnore
    private AdresseDto adresse;

    private String photo;

    private String num_tel;

    private Integer idEntreprise;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur){

        if (fournisseur == null){
            return null;
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .email(fournisseur.getEmail())
                .photo(fournisseur.getPhoto())
                .num_tel(fournisseur.getNum_tel())
                .idEntreprise(fournisseur.getIdEntreprise())
                .build();
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurDto){

        if (fournisseurDto == null){
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setPhoto(fournisseurDto.getPhoto());
        fournisseur.setNum_tel(fournisseurDto.getNum_tel());
        fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());

        return fournisseur;
    }
}
