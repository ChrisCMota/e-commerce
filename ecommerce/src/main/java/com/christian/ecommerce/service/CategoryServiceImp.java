package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.CategoryDAO;
import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.exceptions.CategoryException;
import com.christian.ecommerce.mapper.CategoryMapper;
import com.christian.ecommerce.model.Category;

import java.util.List;

public class CategoryServiceImp implements ICategoryService{

    private CategoryDAO repository;
    private CategoryMapper mapper;

    public CategoryServiceImp(CategoryDAO repository, CategoryMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDTO createNew(CategoryDTO newCategoryDTO) {

        if (newCategoryDTO != null){
            Category category = mapper.dtoToCategory(newCategoryDTO);

            return mapper.categoryToDto(repository.save(category));
        }

        throw new CategoryException("[ERROR]: Could not create category(null)");
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {

        if (categoryDTO != null){
            Category category = mapper.dtoToCategory(categoryDTO);

            return mapper.categoryToDto(repository.save(category));
        }

        throw new CategoryException("[ERROR]: Could not update null category(null)");
    }

    @Override
    public List<CategoryDTO> getAll() {
        return mapper.listCategoryToDto(repository.findAllByOrderByNameAsc());
    }

    @Override
    public void deleteCategory(Integer id) {
        if (id < 0){
            throw new CategoryException("[ERRO]: ID cannot be less than 0");
        }

        repository.deleteById(id);
    }
}
