package com.christian.ecommerce.controller;

import com.christian.ecommerce.dao.ProductDAO;
import com.christian.ecommerce.dto.ProductDTO;
import com.christian.ecommerce.mapper.CategoryMapperImpl;
import com.christian.ecommerce.mapper.ProductMapper;
import com.christian.ecommerce.mapper.ProductMapperImpl;
import com.christian.ecommerce.model.Product;
import com.christian.ecommerce.service.ProductServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebMvcTest(controllers = ProductController.class)
@Import({ObjectMapper.class, ProductMapperImpl.class, ProductServiceImp.class, CategoryMapperImpl.class})
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ProductDAO repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductMapper mapper;

    List<Product> list = new ArrayList<>();

    @BeforeEach
    void init(){
        Product product1 = Product.builder().id(1).price(BigDecimal.valueOf(3000)).name("MacBook Pro").build();
        Product product2 = Product.builder().id(2).price(BigDecimal.valueOf(4000)).name("Colar").build();
        Product product3 = Product.builder().id(3).price(BigDecimal.valueOf(100)).name("Pillow").build();
        list.addAll(List.of(product1, product2, product3));
    }

    @Test
    void shouldReturnAllProducts() throws Exception{
        List<ProductDTO> productDTOS = mapper.productListToDTOList(list);

        BDDMockito.given(repository.findAll()).willReturn(list);

        mvc.perform(MockMvcRequestBuilders.get("/products"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(productDTOS)));
    }

    @Test
     void shouldReturnProductByKeyWord() throws Exception {
        List<ProductDTO> productDTOS = mapper.productListToDTOList(list);

        String res = objectMapper.writeValueAsString(Collections.singletonList(list.get(0)));

        String keyWord = "Mac";

        BDDMockito.given(repository.findByNameContaining(keyWord)).willReturn(List.of(list.get(0)));

        mvc.perform(MockMvcRequestBuilders.get("/products/search").
                        param("keyWord", keyWord)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(res));
    }

}


















