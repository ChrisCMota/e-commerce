package com.christian.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class OrderDTO {


    private Integer number;

    private LocalDate date;

    private BigDecimal grossValue;

    private BigDecimal discount;

    private BigDecimal totalValue;

    private Integer status;

    private CustomerDTO customer;

    private List<ItemOrderDTO> items;
}
