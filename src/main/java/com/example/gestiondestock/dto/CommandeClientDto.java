package com.example.gestiondestock.dto;

import com.example.gestiondestock.model.CommandeClient;
import com.example.gestiondestock.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private ClientDto client;

    private Integer idEntreprise;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient){

        if (commandeClient == null){
            return null;
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .idEntreprise(commandeClient.getIdEntreprise())
                .dateCommande(commandeClient.getDateCommande())
                .etatCommande(commandeClient.getEtatCommande())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .idEntreprise(commandeClient.getIdEntreprise())
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto){

        if (commandeClientDto == null){
            return null;
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setEtatCommande(commandeClient.getEtatCommande());
        commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());

        return commandeClient;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
