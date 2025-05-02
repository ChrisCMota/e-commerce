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
import java.util.Optional;

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
        Category category = new Category(4, "");
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

    @Test
    void shouldUpdateCategory(){
        Category category = new Category(1, "Game");
        CategoryDTO categoryDTO = mapper.categoryToDto(category);

        Category updated = new Category(1, "Game online");
        CategoryDTO updatedDTO = mapper.categoryToDto(updated);

        BDDMockito.given(repository.findById(updated.getId())).willReturn(Optional.of(category));
        BDDMockito.given(repository.save(updated)).willReturn(updated);

        CategoryDTO result = service.update(updatedDTO);

        Assertions.assertThat(result).isEqualTo(updatedDTO);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isNotEqualTo(categoryDTO.getName());

        BDDMockito.then(repository).should().save(updated);
    }




    //TODO: Change repository MOCK to FAKE repository













}