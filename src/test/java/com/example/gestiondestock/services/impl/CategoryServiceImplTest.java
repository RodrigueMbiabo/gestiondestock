package com.example.gestiondestock.services.impl;

import com.example.gestiondestock.dto.CategoryDto;
import com.example.gestiondestock.exception.EntityNotFoundException;
import com.example.gestiondestock.exception.ErrorCodes;
import com.example.gestiondestock.exception.InvalidEntityException;
import com.example.gestiondestock.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService service;

    @Test
    public void shouldSaveCategoryWithSuccess(){
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();

        CategoryDto savedCategory = service.save(expectedCategory);

        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getId());
        assertEquals(expectedCategory.getCode(), savedCategory.getCode());
        assertEquals(expectedCategory.getDesignation(), savedCategory.getDesignation());
        assertEquals(expectedCategory.getIdEntreprise(), savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldUpdateCategoryWithSuccess(){
        CategoryDto expectedCategory = CategoryDto.builder()
                .code("Cat test")
                .designation("Designation test")
                .idEntreprise(1)
                .build();

        CategoryDto savedCategory = service.save(expectedCategory);

        CategoryDto categoryToUpdate = savedCategory;
        categoryToUpdate.setCode("Cat update");

        assertNotNull(categoryToUpdate);
        assertNotNull(categoryToUpdate.getId());
        assertEquals(categoryToUpdate.getCode(), savedCategory.getCode());
        assertEquals(categoryToUpdate.getDesignation(), savedCategory.getDesignation());
        assertEquals(categoryToUpdate.getIdEntreprise(), savedCategory.getIdEntreprise());
    }

    @Test
    public void shouldThrowInvalidEntityException(){
        CategoryDto expectedCategory = CategoryDto.builder().build();

        InvalidEntityException expectedException = assertThrows(InvalidEntityException.class, () -> service.save(expectedCategory));

        assertEquals(ErrorCodes.CATEGORY_NOT_VALID, expectedException.getErrorCodes());
        assertEquals(1, expectedException.getErrors().size());
        assertEquals("Veuillez renseignez le code de la categorie", expectedException.getErrors().get(0));
    }

    @Test
    public void shouldThrowEntityNotFoundException() {
        EntityNotFoundException expectedException = assertThrows(EntityNotFoundException.class, () -> service.findById(0));

        assertEquals(ErrorCodes.CATEGORY_NOT_FOUND, expectedException.getErrorCodes());
        assertEquals("Aucune categorie avec l'id 0 n'a ete trouve", expectedException.getMessage());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundException2() {
        service.findById(0);
    }
}
