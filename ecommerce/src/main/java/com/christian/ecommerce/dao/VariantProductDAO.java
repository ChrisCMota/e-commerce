package com.christian.ecommerce.dao;

import com.christian.ecommerce.model.Product;
import com.christian.ecommerce.model.VariantProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VariantProductDAO extends CrudRepository<VariantProduct, Integer> {
     List<VariantProduct> findByProduct(Product product);
}
