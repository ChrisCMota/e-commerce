package com.christian.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductDTO {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private BigDecimal price;

    private Integer featured;

    private Integer available;
}
