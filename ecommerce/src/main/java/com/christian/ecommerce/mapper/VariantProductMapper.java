package com.christian.ecommerce.mapper;

import com.christian.ecommerce.dto.VariantProductDTO;
import com.christian.ecommerce.model.VariantProduct;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VariantProductMapper {

    VariantProduct variantDtoToVariant(VariantProductDTO variantProductDTO);

    VariantProductDTO variantToVariantDto(VariantProduct variantProduct);

    List<VariantProduct> variantDtoListToVariantList(List<VariantProductDTO> variantProductDTOS);

    List<VariantProductDTO> variantListToVariantDtoList(List<VariantProduct> variantProducts);
}
