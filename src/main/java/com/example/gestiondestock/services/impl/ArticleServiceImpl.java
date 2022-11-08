package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.ArticleDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.repository.ArticleRepository;
import com.example.gestiondestock.services.ArticleService;
import com.example.gestiondestock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()){
            log.error("L'article n'est pas valide {}",articleDto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null){
            log.error("Article ID is null");
            return null;
        }

        return ArticleDto.fromEntity(articleRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Aucun article avec l'id " + id + " n'a ete trouve", ErrorCodes.ARTICLE_NOT_FOUND))
                );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (codeArticle == null){
            log.error("Article ID is null");
            return null;
        }

        return ArticleDto.fromEntity(articleRepository.findByCodeArticle(codeArticle).orElseThrow(() ->
                new EntityNotFoundException("Aucun article avec le code article " + codeArticle + " n'a ete trouve", ErrorCodes.ARTICLE_NOT_FOUND))
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
