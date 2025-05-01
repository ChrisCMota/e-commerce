package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.CategoryDAO;
import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.exceptions.CategoryException;
import com.christian.ecommerce.mapper.CategoryMapper;
import com.christian.ecommerce.mapper.CategoryMapperImpl;
import com.christian.ecommerce.model.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImpTest {

    @Mock
    private CategoryDAO repository;

    private CategoryMapper mapper = new CategoryMapperImpl();

    @InjectMocks
    private CategoryServiceImp service = new CategoryServiceImp(repository, mapper);

    private List<Category> categories = new ArrayList<>();
    private List<CategoryDTO> categoriesDTO = new ArrayList<>();

    @BeforeEach
    void init(){
        Category category1 = new Category(1, "Game");
        Category category2 = new Category(2, "Kitchen");
        Category category3 = new Category(3, "Technology");

        categories.addAll(List.of(category1, category2, category3));

        categoriesDTO = mapper.listCategoryToDto(categories);
    }

    @Test
    void shouldCreateNewCategory(){
        Category category = new Category(4, "Home");
        CategoryDTO newCategoryDTo = mapper.categoryToDto(category);
        CategoryDTO wrongCategory = new CategoryDTO(4, "Wrong");

        BDDMockito.given(repository.save(category)).willReturn(category);

        CategoryDTO returnedCategory = service.createNew(newCategoryDTo);

        Assertions.assertThat(returnedCategory).isEqualTo(newCategoryDTo);
        Assertions.assertThat(returnedCategory).isNotEqualTo(wrongCategory);

        BDDMockito.then(repository).should().save(category);
    }

    @Test
    void shouldThrowNullCategoryException(){
        Category category = null;
        CategoryDTO categoryDTO = null;

        Assertions.assertThatThrownBy(() -> service.createNew(categoryDTO))
                .isInstanceOf(CategoryException.class)
                .hasMessage("[ERROR]: Could not create category(null)");
    }


















}