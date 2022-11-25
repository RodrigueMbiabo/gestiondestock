package com.example.gestiondestock.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Embedded
    private Adresse adresse;

    @Column(name = "code_fiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String photo;

    @Column(name = "email")
    private String email;

    @Column(name = "num_tel")
    private String numTel;

    @Column(name = "site_web")
    private String siteWeb;

    @OneToMany(mappedBy = "entreprise")
    private List<Utilisateur> utilisateurs;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getCodeFiscal() {
        return codeFiscal;
    }

    public void setCodeFiscal(String codeFiscal) {
        this.codeFiscal = codeFiscal;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    @Override
    public String toString() {
        return "Entreprise{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", adresse=" + adresse +
                ", codeFiscal='" + codeFiscal + '\'' +
                ", photo='" + photo + '\'' +
                ", email='" + email + '\'' +
                ", numTel='" + numTel + '\'' +
                ", siteWeb='" + siteWeb + '\'' +
                ", utilisateurs=" + utilisateurs +
                '}';
    }
}
