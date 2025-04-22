package com.christian.ecommerce.service;

import com.christian.ecommerce.model.Customer;

import java.util.List;

public interface ICustomerService {

    Customer createNewCustomer(Customer newCustomer);
    Customer updateCustomer(Customer customer);
    Customer getCustomerById(Integer id);
    Customer getCustomerByPhoneNumber(String phoneNumber);
    List<Customer> findAll();
}
