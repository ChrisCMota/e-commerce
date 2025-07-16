package com.christian.ecommerce.service;

import com.christian.ecommerce.dto.VariantProductDTO;

public interface IVariantProductService {
    VariantProductDTO addNew(VariantProductDTO variantProductDTO);
    VariantProductDTO updateVariant(VariantProductDTO variantProductDTO);
}
