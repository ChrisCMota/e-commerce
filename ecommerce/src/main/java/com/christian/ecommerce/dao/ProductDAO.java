package com.christian.ecommerce.dao;

import com.christian.ecommerce.model.Category;
import com.christian.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    List<Product> findByNameContaining(String keyWord);
    Page<Product> findByOrderByNameAsc(Pageable pageable);
    List<Product> findByCategoriesContaining(Category category);
}
