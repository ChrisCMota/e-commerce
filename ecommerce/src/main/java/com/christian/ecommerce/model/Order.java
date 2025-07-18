package com.christian.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer number;

    @Column(name = "date_order")
    private LocalDate date;

    @Column(name = "gross_value")
    private BigDecimal grossValue;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
}
