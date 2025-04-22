package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.CustomerDAO;
import com.christian.ecommerce.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements ICustomerService{

    private CustomerDAO customerDAO;

    public CustomerServiceImp(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer createNewCustomer(Customer newCustomer) {
        return customerDAO.save(newCustomer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerDAO.findById(id).orElse(null);
    }

    @Override
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return customerDAO.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerDAO.findAll();
    }
}
