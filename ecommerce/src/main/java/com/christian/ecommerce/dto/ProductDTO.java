package com.christian.ecommerce.dto;

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

    @NotBlank(message = "name cannot be null or blank")
    private String name;

    private String description;

    @NotBlank(message = "price cannot be null or blank")
    private BigDecimal price;

    private Integer featured;

    private Integer available;

    private List<CategoryDTO> categories;
}
