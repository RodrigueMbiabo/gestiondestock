package com.example.gestiondestock.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvt_stk")
public class MvtStk extends AbstractEntity{

    @Column(name = "date_mvt")
    private Instant datMvt;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "id_entreprise")
    private Integer idEntreprise;

    @Column(name = "type_mvtStk")
    private TypeMvtStk typeMvtStk;
}
