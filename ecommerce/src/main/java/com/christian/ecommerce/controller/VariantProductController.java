package com.christian.ecommerce.controller;

import com.christian.ecommerce.dto.ProductDTO;
import com.christian.ecommerce.dto.VariantProductDTO;
import com.christian.ecommerce.service.IVariantProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class VariantProductController {
    private IVariantProductService service;

    public VariantProductController(IVariantProductService service){
        this.service = service;
    }

    @PostMapping("variants")
    public ResponseEntity<VariantProductDTO> addNewVariant(@Valid @RequestBody VariantProductDTO variantProductDTO){
        log.info("[INFO]: add new variant requested");

        VariantProductDTO variantProductDTOAdded = service.addNew(variantProductDTO);

        return ResponseEntity.ok(variantProductDTO);
    }

    @PutMapping("variants")
    public ResponseEntity<VariantProductDTO> updateVariant(@Valid @RequestBody VariantProductDTO variantProductDTO){
        log.info("[INFO]: update variant requested");

        VariantProductDTO variantProductDTOUpdated = service.updateVariant(variantProductDTO);

        return ResponseEntity.ok(variantProductDTOUpdated);
    }

    @GetMapping("variant/{id}")
    public ResponseEntity<VariantProductDTO> getById(@PathVariable Integer id){
        log.info("[INFO]: get variant by id requested");

        VariantProductDTO variantProductDTO = service.getById(id);

        return ResponseEntity.ok(variantProductDTO);
    }

    @GetMapping("variants")
    public ResponseEntity<List<VariantProductDTO>> getAllByProduct(@RequestParam(name = "idproduct") Integer idProduct){
        log.info("[INFO]: get all variants by product requested");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(idProduct);

        return ResponseEntity.ok(service.getByProduct(productDTO));
    }
}
