package com.christian.ecommerce.dto;

import com.christian.ecommerce.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VariantProductDTO {

    @JsonProperty("")
    private Integer id;

    private String name;

    private String description;

    private String linkPhoto;

    private Product product;
}
