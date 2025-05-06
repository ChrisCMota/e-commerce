package com.christian.ecommerce.controller;

import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.mapper.CategoryMapper;
import com.christian.ecommerce.mapper.CategoryMapperImpl;
import com.christian.ecommerce.repository.FakeCategoryRepository;
import com.christian.ecommerce.service.CategoryServiceImp;
import com.christian.ecommerce.service.ICategoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

class CategoryControllerTest {

    private FakeCategoryRepository repository = new FakeCategoryRepository();
    private CategoryMapper mapper = new CategoryMapperImpl();
    private ICategoryService service = new CategoryServiceImp(repository, mapper);
    private CategoryController categoryController = new CategoryController(service);

    @Test
    void shouldGetAllCategories(){
        ResponseEntity<List<CategoryDTO>> allCategories = categoryController.getAllCategories();

        Assertions.assertThat(allCategories).isNotNull()
                .extracting(ResponseEntity::getBody)
                .isEqualTo(service.getAll());
    }

    @Test
    void shouldCreateNewCategory(){
        CategoryDTO newCategoryDTO = new CategoryDTO(4, "New Category");

        ResponseEntity<CategoryDTO> result = categoryController.createCategory(newCategoryDTO);

        Assertions.assertThat(result).isNotNull()
                .extracting(ResponseEntity::getBody)
                .isEqualTo(newCategoryDTO);
    }

    @Test
    void shouldUpdateCategory(){
        CategoryDTO updatedCategory = new CategoryDTO(1, "Game Updated");

        ResponseEntity<CategoryDTO> result = categoryController.updateCategory(updatedCategory);

        Assertions.assertThat(result).isNotNull()
                .extracting(ResponseEntity::getBody)
                .isEqualTo(updatedCategory);
    }

    @Test
    void shouldDeleteCategoryById(){
        CategoryDTO categoryDTO = new CategoryDTO(1, "Game");

        categoryController.deleteCategoryById(1);
        ResponseEntity<List<CategoryDTO>> allCategories = categoryController.getAllCategories();

        Assertions.assertThat(allCategories).isNotNull()
                .extracting(ResponseEntity::getBody)
                .satisfies(list -> {
                    Assertions.assertThat(list).isNotEmpty();
                    Assertions.assertThat(list).doesNotContain(categoryDTO);
                });

    }


}