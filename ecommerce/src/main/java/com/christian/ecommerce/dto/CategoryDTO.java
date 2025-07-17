package com.christian.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoryDTO {

    private Integer id;

    @NotBlank(message = "name cannot be null or blank")
    private String name;
}
