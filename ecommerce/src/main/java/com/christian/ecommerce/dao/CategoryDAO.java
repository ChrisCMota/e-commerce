package com.christian.ecommerce.dao;

import com.christian.ecommerce.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDAO extends CrudRepository<Category, Integer> {
    public List<Category> findAllByOrderByNameAsc();
}
