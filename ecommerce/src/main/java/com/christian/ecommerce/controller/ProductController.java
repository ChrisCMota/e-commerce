package com.christian.ecommerce.controller;

import com.christian.ecommerce.dto.CategoryDTO;
import com.christian.ecommerce.dto.ProductDTO;
import com.christian.ecommerce.service.IProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDTO>> getAll(){
        log.info("INFO: getAll() Requested");

        List<ProductDTO> productDTOS = productService.findAll();

        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<ProductDTO>> getProductsByKeyWord(@RequestParam String keyWord){
        log.info("INFO: search product by key word requested '{}'", keyWord);

        List<ProductDTO> productDTOS = productService.findByKeyWord(keyWord);

        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Integer id){
        log.info("INFO: get product by id requested '{}'", id);

        ProductDTO byId = productService.findById(id);

        return ResponseEntity.ok(byId);
    }

    @GetMapping("products/category/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Integer id){
        log.info("INFO: get products by category requested '{}'", id);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);

        List<ProductDTO> productsByCategory = productService.findByCategory(categoryDTO);

        return ResponseEntity.ok(productsByCategory);
    }

    @PostMapping("products")
    public ResponseEntity<ProductDTO> insertProduct(@Valid @RequestBody ProductDTO newProductDTO){
        log.info("INFO: insert product requested '{}'", newProductDTO);

        ProductDTO productDTOReturned = productService.createNewProduct(newProductDTO);

        return ResponseEntity.ok().body(productDTOReturned);
    }

    @PutMapping("products")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO){
        log.info("INFO: update product request '{}'", productDTO.toString());

        ProductDTO productDTOUpdated = productService.updateProduct(productDTO);

        return ResponseEntity.ok(productDTOUpdated);
    }

}