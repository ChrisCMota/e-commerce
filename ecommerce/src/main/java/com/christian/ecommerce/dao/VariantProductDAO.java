package com.christian.ecommerce.dao;

import com.christian.ecommerce.model.VariantProduct;
import org.springframework.data.repository.CrudRepository;

public interface VariantProductDAO extends CrudRepository<VariantProduct, Integer> {
}
