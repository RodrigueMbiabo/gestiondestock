package com.example.gestiondestock.utils;

public interface Constants {

    String APP_ROOT = "gestiondestock/v1";

    String COMMANDE_FOURNISSEUR_ENDPOINT = APP_ROOT + "/commandesFournisseurs";

    String CREATE_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/create";

    String FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}";

    String FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/{codeCommandeFournisseur}";

    String FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/all";

    String DELETE_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT = COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}";

    String AUTHENTICATION_ENDPOINT = APP_ROOT+ "/auth";
}
