package com.christian.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductDTO {

    private Integer id;

    @NotBlank
    @JsonProperty("name_product")
    private String name;

    @JsonProperty("description_product")
    private String description;

    @NotBlank
    @JsonProperty("price_product")
    private BigDecimal price;

    private Integer featured;

    private Integer available;

    private List<CategoryDTO> categories;
}
