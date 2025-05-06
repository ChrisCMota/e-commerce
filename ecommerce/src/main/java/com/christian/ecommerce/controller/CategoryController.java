package com.christian.ecommerce.controller;

import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CategoryController {

    private ICategoryService service;

    public CategoryController(ICategoryService service){
        this.service = service;
    }

    @GetMapping("categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        log.info("[INFO]: getAllCategories() requested");

        List<CategoryDTO> categories = service.getAll();

        return ResponseEntity.ok().body(categories);
    }

    @PostMapping("categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("[INFO]: createCategory() requested");

        CategoryDTO newCategory = service.createNew(categoryDTO);

        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("categories")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("[INFO]: updateCategory() requested");

        CategoryDTO updatedCategory = service.update(categoryDTO);

        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Integer id){
        log.info("[INFO]: deleteCategoryById() requested");

        service.deleteCategory(id);

        return ResponseEntity.status(204).build();
    }

}



















