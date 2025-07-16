package com.christian.ecommerce.service;

import com.christian.ecommerce.dto.VariantProductDTO;
import com.christian.ecommerce.model.VariantProduct;

public interface IVariantProduct {
    VariantProduct addNew(VariantProductDTO variantProductDTO);
    VariantProduct updateVariant(VariantProductDTO variantProductDTO);
}
