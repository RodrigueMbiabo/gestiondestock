package com.example.gestiondestock.services;

import com.example.gestiondestock.dto.CommandeClientDto;
import com.example.gestiondestock.model.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto articleDto);

    CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

    CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

    CommandeClientDto updateClient(Integer idCommande, Integer idClient);

    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    void delete(Integer id);
}
