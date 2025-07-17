package com.christian.ecommerce.service;

import com.christian.ecommerce.dto.ProductDTO;
import com.christian.ecommerce.dto.VariantProductDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IVariantProductService {
    VariantProductDTO addNew(VariantProductDTO variantProductDTO);
    VariantProductDTO updateVariant(VariantProductDTO variantProductDTO);
    List<VariantProductDTO> getByProduct(ProductDTO productDTO);
    VariantProductDTO getById(Integer id);

}
