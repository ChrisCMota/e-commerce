package com.christian.ecommerce.dto;

import com.christian.ecommerce.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class VariantProductDTO {

    private Integer id;

    @NotBlank
    private String name;

    private String description;

    private String linkPhoto;

    private Product product;
}
