package com.example.gestiondestock.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
public class Client extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Embedded
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "id_entreprise")
    private Integer idEntreprise;

    @Column(name = "num_tel")
    private String num_tel;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;
}
