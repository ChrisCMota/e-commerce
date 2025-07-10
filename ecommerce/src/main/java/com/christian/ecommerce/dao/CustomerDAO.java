package com.christian.ecommerce.dao;

import com.christian.ecommerce.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends CrudRepository<Customer, Integer> {

    Customer findByPhoneNumber(String phoneNumber);
    Customer findByEmail(String email);
}
