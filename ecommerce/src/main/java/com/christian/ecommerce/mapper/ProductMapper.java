package com.christian.ecommerce.mapper;

import com.christian.ecommerce.dto.ProductDTO;
import com.christian.ecommerce.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product productDtoToProduct(ProductDTO productDTO);

    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productListToDTOList(List<Product> products);

    Page<Product> dtoProdPageToProdPage(Page<ProductDTO> productDTOPage);

    Page<ProductDTO> prodPageToDtoProdPage(Page<Product> productPage);
}
