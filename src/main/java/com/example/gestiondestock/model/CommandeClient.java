package com.example.gestiondestock.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commande_client")
public class CommandeClient extends AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @Column(name = "etat_commande")
    private EtatCommande etatCommande;

    @Column(name = "id_entreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "commandeClient")
    private List<LigneCommandeClient> ligneCommandeClients;
}
