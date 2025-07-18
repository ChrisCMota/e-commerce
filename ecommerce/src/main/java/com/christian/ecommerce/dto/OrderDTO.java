package com.christian.ecommerce.dto;

import com.christian.ecommerce.model.Customer;
import com.christian.ecommerce.model.ItemOrder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDTO {


    private Integer number;

    private LocalDate date;

    private BigDecimal grossValue;

    private BigDecimal discount;

    private BigDecimal totalValue;

    private Integer status;

    private Customer customer;

    private List<ItemOrder> items;
}
