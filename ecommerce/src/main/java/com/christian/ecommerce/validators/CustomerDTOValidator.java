package com.christian.ecommerce.validators;

import com.christian.ecommerce.dto.CustomerDTO;
import com.christian.ecommerce.exceptions.CustomersException;

public class CustomerDTOValidator {

    static public void validator(CustomerDTO customerDTO){
        if (customerDTO.getName() == null ||
                customerDTO.getEmail() == null ||
                customerDTO.getAddress() == null ||
                customerDTO.getEircode() == null ||
                customerDTO.getPassword() == null ||
                customerDTO.getPassword().isBlank() ||
                customerDTO.getName().isBlank() ||
                customerDTO.getEmail().isBlank()||
                customerDTO.getAddress().isBlank() ||
                customerDTO.getEircode().isBlank()){
            throw new CustomersException("Invalid CustomerDTO");
        }
    }


}
