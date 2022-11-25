package com.example.gestiondestock.controller.api;

import com.example.gestiondestock.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.gestiondestock.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/category/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/category/getCategoryById/{idCategory}", produces =  MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("idCategory") Integer id);

    @GetMapping(value = APP_ROOT + "/category/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/category/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Integer id);
}
