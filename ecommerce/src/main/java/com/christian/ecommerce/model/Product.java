package com.christian.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer id;

    @Column(name = "name_product", length = 50, nullable = false)
    private String name;

    @Column(name = "description_product", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price_product")
    private BigDecimal price;

    @Column(name = "featured")
    private Integer featured;

    @Column(name = "available")
    private Integer available;

    @ManyToMany
    @JoinTable(name = "category_products",
               joinColumns = @JoinColumn(name = "id_product"),
               inverseJoinColumns = @JoinColumn(name = "id_category"))
    private List<Category> categories;

}












