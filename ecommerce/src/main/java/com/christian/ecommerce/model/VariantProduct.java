package com.christian.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variant_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class VariantProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variant")
    private Integer id;

    @Column(name = "name_variant", length = 50, nullable = false)
    private String name;

    @Column(name = "description_variant", columnDefinition = "TEXT")
    private String description;

    @Column(name = "link_photo", length = 255)
    private String linkPhoto;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
}
