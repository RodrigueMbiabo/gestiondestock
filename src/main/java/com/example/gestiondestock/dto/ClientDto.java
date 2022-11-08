package com.example.gestiondestock.dto;

import com.example.gestiondestock.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    @JsonIgnore
    private AdresseDto adresse;

    private String photo;

    private Integer idEntreprise;

    private String num_tel;

    @JsonIgnore
    private List<CommandeClientDto> commandeClients;

    public static ClientDto fromEntity(Client client){

        if (client == null){
            return null;
        }

        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .email(client.getEmail())
                .photo(client.getPhoto())
                .idEntreprise(client.getIdEntreprise())
                .num_tel(client.getNum_tel())
                .build();
    }

    public static Client toEntity(ClientDto clientDto){

        if (clientDto != null){
            return null;
        }

        Client client = new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setEmail(clientDto.getPrenom());
        client.setPhoto(clientDto.getPhoto());
        client.setIdEntreprise(clientDto.getIdEntreprise());
        client.setNum_tel(clientDto.getNum_tel());
        client.setNum_tel(client.getNum_tel());

        return client;
    }
}
