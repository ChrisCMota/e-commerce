package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.VariantProductDAO;
import com.christian.ecommerce.dto.ProductDTO;
import com.christian.ecommerce.dto.VariantProductDTO;
import com.christian.ecommerce.exceptions.VariantProductException;
import com.christian.ecommerce.mapper.ProductMapper;
import com.christian.ecommerce.mapper.VariantProductMapper;
import com.christian.ecommerce.model.Product;
import com.christian.ecommerce.model.VariantProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantProductService implements IVariantProductService {

    private VariantProductDAO repository;
    private VariantProductMapper mapperVariant;
    private ProductMapper mapperProduct;

    public VariantProductService(VariantProductDAO repository, VariantProductMapper mapper, ProductMapper productMapper){
        this.mapperVariant = mapper;
        this.repository = repository;
        this.mapperProduct = productMapper;
    }

    @Override
    public VariantProductDTO addNew(VariantProductDTO variantProductDTO) {

        VariantProduct variantProduct = mapperVariant.variantDtoToVariant(variantProductDTO);

        VariantProductDTO variantProductDTOSaved = mapperVariant.variantToVariantDto(repository.save(variantProduct));

        return variantProductDTOSaved;
    }

    @Override
    public VariantProductDTO updateVariant(VariantProductDTO variantProductDTO) {
        VariantProduct variantProduct = mapperVariant.variantDtoToVariant(variantProductDTO);

        VariantProductDTO variantProductDTOSaved = mapperVariant.variantToVariantDto(repository.save(variantProduct));

        if (variantProductDTOSaved != null) {
            return variantProductDTOSaved;
        }

        throw new VariantProductException("[ERROR]: could not update variant product");
    }

    @Override
    public List<VariantProductDTO> getByProduct(ProductDTO productDTO) {
        Product product = mapperProduct.productDtoToProduct(productDTO);

        List<VariantProductDTO> foundByProduct = mapperVariant.variantListToVariantDtoList(repository.findByProduct(product));

        if (foundByProduct != null) {
            return foundByProduct;
        }

        throw new VariantProductException("[ERROR]: could get variant by product");
    }

    @Override
    public VariantProductDTO getById(Integer id) {
        VariantProductDTO variantProduct = mapperVariant.variantToVariantDto(repository.findById(id).orElse(null));

        if (variantProduct != null) {
            return variantProduct;
        }

        throw new VariantProductException("[ERROR]: could not get variant by id");
    }
}













