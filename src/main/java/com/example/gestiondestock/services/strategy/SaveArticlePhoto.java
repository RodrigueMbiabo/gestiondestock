package com.example.gestiondestock.services.strategy;

import com.example.gestiondestock.dto.ArticleDto;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidOperationException;
import com.example.gestiondestock.services.ArticleService;
import com.example.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("articleStrategy")
@Slf4j
public class SaveArticlePhoto implements Strategy<ArticleDto>{

    private FlickrService flickrService;
    private ArticleService articleService;

    @Autowired
    public SaveArticlePhoto(FlickrService flickrService, ArticleService articleService) {
        this.flickrService = flickrService;
        this.articleService = articleService;
    }

    @Override
    public ArticleDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ArticleDto article = articleService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        article.setPhoto(urlPhoto);
        return articleService.save(article);
    }
}
