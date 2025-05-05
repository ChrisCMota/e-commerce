package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.CategoryDAO;
import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.exceptions.CategoryException;
import com.christian.ecommerce.mapper.CategoryMapper;
import com.christian.ecommerce.mapper.CategoryMapperImpl;
import com.christian.ecommerce.model.Category;
import com.christian.ecommerce.repository.FakeCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CategoryServiceImpTest {


    private CategoryDAO repository = new FakeCategoryRepository();

    private CategoryMapper mapper = new CategoryMapperImpl();

    private CategoryServiceImp service = new CategoryServiceImp(repository, mapper);


    @Test
    void shouldCreateNewCategory(){
        Category category = new Category(4, "Game Online");
        CategoryDTO newCategoryDTo = mapper.categoryToDto(category);

        CategoryDTO returnedCategory = service.createNew(newCategoryDTo);

        Assertions.assertThat(returnedCategory).isEqualTo(newCategoryDTo);
        Assertions.assertThat(returnedCategory).isNotNull();
        Assertions.assertThat(repository.findAllByOrderByNameAsc()).contains(category);
    }

    @Test
    void shouldThrowNullCategoryExceptionWhenCreateNewCategory(){
        Category category = null;
        CategoryDTO categoryDTO = null;

        Assertions.assertThatThrownBy(() -> service.createNew(categoryDTO))
                .isInstanceOf(CategoryException.class)
                .hasMessage("[ERROR]: Could not create category(null)");
    }

    @Test
    void shouldThrowCategoryAlreadyExistsWhenCreateNewCategory(){
        Category categoryExistent = new Category(1, "Game");
        CategoryDTO categoryExistentDTO = mapper.categoryToDto(categoryExistent);

        Assertions.assertThatThrownBy(() -> service.createNew(categoryExistentDTO))
                .isInstanceOf(CategoryException.class)
                .hasMessage("[ERROR]: This category already exists");
    }

    @Test
    void shouldUpdateCategory(){
        Category updated = new Category(1, "Make-up");
        CategoryDTO updatedDTO = mapper.categoryToDto(updated);

        CategoryDTO result = service.update(updatedDTO);

        Assertions.assertThat(result).isEqualTo(updatedDTO);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(repository.findAllByOrderByNameAsc()).contains(updated);
    }

    @Test
    void shouldThrowNullUpdateCategory(){
        CategoryDTO nullCategoryDTO = null;

        Assertions.assertThatThrownBy(() -> service.update(nullCategoryDTO))
                .isInstanceOf(CategoryException.class)
                .hasMessage("[ERROR]: Could not update null category(null)");
    }

    @Test
    void shouldReturnAllCategories(){
        List<CategoryDTO> categories = new ArrayList<>( List.of(new CategoryDTO(1, "Game"),
                new CategoryDTO(2, "Kitchen"),
                new CategoryDTO(3, "Technology")));

        List<CategoryDTO> result = service.getAll();

        Assertions.assertThat(result).containsAll(categories);
        Assertions.assertThat(result).isEqualTo(categories);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    void shouldDeleteCategory(){
        CategoryDTO categoryDeleted = new CategoryDTO(1, "Game");
        List<CategoryDTO> categories = service.getAll();
        System.out.println(categories);
        service.deleteCategory(1);

        List<CategoryDTO> result = service.getAll();

        Assertions.assertThat(result).doesNotContain(categories.get(0));
    }

    @Test
    void shouldThrowCategoryExceptionWhenIdLessThenZero(){
        Assertions.assertThatThrownBy(() -> service.deleteCategory(-1))
                .isInstanceOf(CategoryException.class)
                .hasMessage("[ERROR]: ID cannot be less than 0");
    }

    @Test
    void shouldThrowCategoryExceptionWhenCategoryNotFound(){
        Assertions.assertThatThrownBy(() -> service.deleteCategory(99))
                .isInstanceOf(CategoryException.class)
                .hasMessage("[ERROR]: Category not found");
    }



















}