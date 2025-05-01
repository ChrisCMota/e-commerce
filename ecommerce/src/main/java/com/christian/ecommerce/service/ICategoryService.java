package com.christian.ecommerce.service;

import com.christian.ecommerce.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createNew(CategoryDTO newCategory);
    CategoryDTO update(CategoryDTO category);
    List<CategoryDTO> getAll();
    void deleteCategory(Integer id);
}
