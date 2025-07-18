package com.christian.ecommerce.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ItemOrderDTO {

    private Integer numSeq;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private VariantProductDTO variantProduct;

    private OrderDTO order;
}
