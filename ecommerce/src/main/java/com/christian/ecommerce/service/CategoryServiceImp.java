package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.CategoryDAO;
import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.exceptions.CategoryException;
import com.christian.ecommerce.mapper.CategoryMapper;
import com.christian.ecommerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements ICategoryService {

    private CategoryDAO repository;
    private CategoryMapper mapper;

    public CategoryServiceImp(CategoryDAO repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDTO createNew(CategoryDTO newCategoryDTO) {

        if (newCategoryDTO == null) {
            throw new CategoryException("[ERROR]: Could not create category(null)");
        }

        Optional<Category> category1 = repository.findAllByOrderByNameAsc().stream()
                .filter(category -> category.getName().equals(newCategoryDTO.getName()))
                .findFirst();

        if (category1.isPresent()) {
            throw new CategoryException("[ERROR]: This category already exists");
        }

        Category category = mapper.dtoToCategory(newCategoryDTO);

        return mapper.categoryToDto(repository.save(category));
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {

        if (categoryDTO == null) {
            throw new CategoryException("[ERROR]: Could not update null category(null)");
        }

        Category category = mapper.dtoToCategory(categoryDTO);

        Category byId = repository.findById(categoryDTO.getId())
                .orElseThrow(() -> new CategoryException("[ERROR]: Category not found"));

        byId.setName(categoryDTO.getName());

        return mapper.categoryToDto(repository.save(byId));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return mapper.listCategoryToDto(repository.findAllByOrderByNameAsc());
    }

    @Override
    public void deleteCategory(Integer id) {
        if (id < 0) {
            throw new CategoryException("[ERROR]: ID cannot be less than 0");
        }

        Optional<Category> byId = repository.findById(id);

        if (byId.isEmpty()) {
            throw new CategoryException("[ERROR]: Category not found");
        }

        repository.deleteById(id);
    }
}
