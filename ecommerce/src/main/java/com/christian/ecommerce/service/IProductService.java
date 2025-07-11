package com.christian.ecommerce.service;

import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO createNewProduct(ProductDTO newProduct);
    ProductDTO updateProduct(ProductDTO product);
    List<ProductDTO> findAll();
    List<ProductDTO> findByKeyWord(String keyWord);
    ProductDTO findById(Integer id);
    List<ProductDTO> findByCategory(CategoryDTO categoryDTO);
}
