package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.ArticleDto;
import com.example.gestiondestock.dto.LigneCommandeClientDto;
import com.example.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.example.gestiondestock.dto.LigneVenteDto;
import com.example.gestiondestock.model.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.gestiondestock.utils.Constants.APP_ROOT;

@Api("articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/article/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistre un article", notes = "Cette méthode permet d'enregistrer un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'object article a été crée"),
            @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto);


    @GetMapping(value = APP_ROOT + "/article/getArticleById/{idArticle}", produces =  MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recherche un article", notes = "Cette méthode permet de chercher un article par son id", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'object article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'a été trouvé dans la base de donnée avec l'id fourni")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/article/getArticleByCodeArticle/{codeArticle}", produces =  MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recherche un article", notes = "Cette méthode permet de chercher un article par son code", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'object article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'a été trouvé dans la base de donnée avec le code fourni")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/article/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des articles", notes = "Cette méthode permet de chercher et renvoyer une liste d'article qui existe", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste d'artcile est trouvé / la liste d'article est vide")
    })
    List<ArticleDto> findAll();

    @GetMapping(value = APP_ROOT + "/articles/historique/vente/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneVenteDto> findHistoriqueVentes(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/articles/historique/commandeclient/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeClientDto> findHistoriqueCommandeClient(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/articles/historique/commandefournisseur/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/articles/filter/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory);


    @DeleteMapping(value = APP_ROOT + "/article/delete/{idArticle}")
    @ApiOperation(value = "Supprime un article", notes = "Cette méthode permet de supprimer un article qui existe par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à été supprimer avec succès"),
    })
    void delete(@PathVariable("idArticle") Integer id);
}
