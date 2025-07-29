package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.ProductDAO;
import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.dto.ProductDTO;
import com.christian.ecommerce.exceptions.ProductException;
import com.christian.ecommerce.mapper.CategoryMapper;
import com.christian.ecommerce.mapper.ProductMapper;
import com.christian.ecommerce.model.Category;
import com.christian.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements IProductService{

    private ProductDAO productDAO;
    private ProductMapper mapper;
    private CategoryMapper categoryMapper;

    private static final int PAGE_SIZE = 5;

    public ProductServiceImp(ProductDAO productDAO, ProductMapper mapper, CategoryMapper categoryMapper){
        this.productDAO = productDAO;
        this.mapper = mapper;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public ProductDTO createNewProduct(ProductDTO newProduct) {
        Product product = mapper.productDtoToProduct(newProduct);

        ProductDTO savedProductDTO = mapper.productToProductDTO(productDAO.save(product));

        if (savedProductDTO != null){
            return savedProductDTO;
        }
        throw new ProductException("[ERROR]: Could not create new product");
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = mapper.productDtoToProduct(productDTO);

        ProductDTO savedProductDTO = mapper.productToProductDTO(productDAO.save(new Product()));

        if (savedProductDTO != null){
            return savedProductDTO;
        }

        throw new ProductException("[ERROR]: Could not update product");
    }

    @Override
    public Page<Product> findAll(int nPage) {
        Pageable pageable = PageRequest.of(nPage, PAGE_SIZE);

        if (pageable != null) {
            return productDAO.findAll(pageable);
        }

        throw new ProductException("[ERROR]: Could not find list of products");
    }

    @Override
    public ProductDTO findById(Integer id) {
        ProductDTO productDTO = mapper.productToProductDTO(productDAO.findById(id).orElse(null));

        if (productDTO != null){
            return productDTO;
        }

        throw new ProductException("[ERROR]: Could not find product by id");
    }

    @Override
    public List<ProductDTO> findByKeyWord(String keyWord) {
        List<ProductDTO> productDTOSByKeyWord = mapper.productListToDTOList(productDAO.findByNameContaining(keyWord));

        if (productDTOSByKeyWord != null){
            return productDTOSByKeyWord;
        }

        throw new ProductException("[ERROR]: Could not find list of product by keyword");
    }

    @Override
    public List<ProductDTO> findByCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.dtoToCategory(categoryDTO);
        List<ProductDTO> productDTOSByCategories = mapper.productListToDTOList(productDAO.findByCategoriesContaining(category));

        if (productDTOSByCategories != null){
            return productDTOSByCategories;
        }

        throw new ProductException("[ERROR]: Could not find product by category");
    }
}
