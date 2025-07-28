package com.christian.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomerDTO {

    private Integer id;

    @NotBlank(message = "Invalid name")
    private String name;

    @Email(message = "Invalid email")
    @Size(max = 100)
    private String email;

    @Size(max = 20)
    private String phoneNumber;

    @NotBlank(message = "Invalid address")
    private String address;

    @NotBlank(message = "Invalid Eircode")
    @Size(max = 10)
    private String eircode;

    @Size(max = 50)
    private String country;

    @Size(max = 50)
    private String city;
}
