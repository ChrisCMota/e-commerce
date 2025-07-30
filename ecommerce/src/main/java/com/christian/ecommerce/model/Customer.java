package com.christian.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Integer id;

    @Column(name = "name_customer", nullable = false, length = 50)
    private String name;

    @Column(name = "email_customer", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_costumer", length = 100)
    private String password;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "address_customer", nullable = false)
    private String address;

    @Column(name = "eircode_customer", nullable = false, length = 10)
    private String eircode;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "city", length = 50)
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
