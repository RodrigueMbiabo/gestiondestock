package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.ArticleDto;
import com.example.gestiondestock.dto.CategoryDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.repository.CategoryRepository;
import com.example.gestiondestock.services.CategoryService;
import com.example.gestiondestock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if (!errors.isEmpty()){
            log.error("La categorie n'est pas valide {}",categoryDto);
            throw new InvalidEntityException("La categorie n'est pas valide", ErrorCodes.CATEGORY_NOT_FOUND, errors);
        }
        return CategoryDto.fromEntity(
                categoryRepository.save(CategoryDto.toEntity(categoryDto))
        );
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null){
            log.error("category ID is null");
            return null;
        }

        return CategoryDto.fromEntity(categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Aucune categorie avec l'id " + id + " n'a ete trouve", ErrorCodes.CATEGORY_NOT_FOUND)
        ));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("category ID is null");
            return;
        }

        categoryRepository.deleteById(id);
    }
}
