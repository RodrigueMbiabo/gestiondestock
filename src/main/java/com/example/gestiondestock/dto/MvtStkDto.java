package com.example.gestiondestock.dto;

import com.example.gestiondestock.model.MvtStk;
import com.example.gestiondestock.model.TypeMvtStk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MvtStkDto {

    private Integer id;

    @JsonIgnore
    private ArticleDto article;

    private Instant datMvt;

    private BigDecimal quantite;

    private TypeMvtStk typeMvtStk;

    private Integer idEntreprise;

    public static MvtStkDto fromEntity(MvtStk mvtStk){

        if (mvtStk == null){
            return null;
        }

        return MvtStkDto.builder()
                .id(mvtStk.getId())
                .datMvt(mvtStk.getDatMvt())
                .quantite(mvtStk.getQuantite())
                .typeMvtStk(mvtStk.getTypeMvtStk())
                .idEntreprise(mvtStk.getIdEntreprise())
                .build();
    }

    public static MvtStk toEntity(MvtStkDto mvtStkDto){

        if (mvtStkDto == null){
            return null;
        }

        MvtStk mvtStk = new MvtStk();
        mvtStk.setId(mvtStkDto.getId());
        mvtStk.setDatMvt(mvtStkDto.getDatMvt());
        mvtStk.setQuantite(mvtStkDto.getQuantite());
        mvtStk.setTypeMvtStk(mvtStkDto.getTypeMvtStk());
        mvtStk.setIdEntreprise(mvtStkDto.getIdEntreprise());
        return mvtStk;
    }


}
