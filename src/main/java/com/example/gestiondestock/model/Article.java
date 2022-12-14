package com.example.gestiondestock.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article extends AbstractEntity {

    @Column(name = "code_article")
    private String codeArticle;

    @Column(name = "designaton")
    private String designation;

    @Column(name = "prix_unitaire")
    private BigDecimal prixUnitaireHt;

    @Column(name = "taux_tva")
    private BigDecimal tauxTva;

    @Column(name = "prix_unitaire_ttc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "id_entreprise")
    private Integer idEntreprise;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idcategorie")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "article")
    private List<LigneVente> ligneVentes;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "article")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "article")
    private List<MvtStk> mvtStks;
}
