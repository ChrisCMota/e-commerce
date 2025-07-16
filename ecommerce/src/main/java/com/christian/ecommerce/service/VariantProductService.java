package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.VariantProductDAO;
import com.christian.ecommerce.dto.VariantProductDTO;
import com.christian.ecommerce.mapper.VariantProductMapper;
import com.christian.ecommerce.model.VariantProduct;

public class VariantProductService implements IVariantProductService {

    private VariantProductDAO repository;
    private VariantProductMapper mapper;

    public VariantProductService(VariantProductDAO repository, VariantProductMapper mapper){
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public VariantProductDTO addNew(VariantProductDTO variantProductDTO) {
        VariantProduct variantProduct = mapper.variantDtoToVariant(variantProductDTO);

        VariantProductDTO variantProductDTOSaved = mapper.variantToVariantDto(repository.save(variantProduct));

        return variantProductDTOSaved;
    }

    @Override
    public VariantProductDTO updateVariant(VariantProductDTO variantProductDTO) {
        VariantProduct variantProduct = mapper.variantDtoToVariant(variantProductDTO);

        VariantProductDTO variantProductDTOSaved = mapper.variantToVariantDto(repository.save(variantProduct));

        return variantProductDTOSaved;
    }
}
