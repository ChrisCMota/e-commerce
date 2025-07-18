package com.christian.ecommerce.dto;

import java.math.BigDecimal;

public class ItemOrderDTO {

    private Integer numSeq;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private VariantProductDTO variantProduct;

    private OrderDTO order;
}
