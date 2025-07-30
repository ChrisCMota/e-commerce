package com.christian.ecommerce.service;

import com.christian.ecommerce.dto.CustomerDTO;
import com.christian.ecommerce.security.ECToken;

import java.util.List;

public interface ICustomerService {

    CustomerDTO createNewCustomer(CustomerDTO newCustomer);
    CustomerDTO updateCustomer(CustomerDTO customer);
    CustomerDTO getCustomerById(Integer id);
    CustomerDTO getCustomerByPhoneNumber(String phoneNumber);
    List<CustomerDTO> findAll();
    CustomerDTO getCustomerByEmail(String email);
    ECToken doLogin(String email, String password);
}
