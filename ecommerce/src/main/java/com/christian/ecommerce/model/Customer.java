package com.christian.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Integer id;

    @Column(name = "name_customer", nullable = false, length = 50)
    @NotBlank(message = "name cannot be blank or null")
    private String name;

    @Column(name = "email_customer", nullable = false, unique = true, length = 100)
    @NotBlank(message = "email cannot be blank or null")
    @Email(regexp = "expressionHere", message = "The e-mail is invalid")
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "address_customer", nullable = false)
    @NotBlank(message = "address cannot be blank or null")
    private String address;

    @Column(name = "aircode_customer", nullable = false, unique = true, length = 10)
    @NotBlank(message = "aircode cannot be blank or null")
    private String aircode;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "city", length = 50)
    private String city;

}
