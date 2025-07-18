package com.christian.ecommerce.dao;

import com.christian.ecommerce.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDAO extends CrudRepository<Order, Integer> {
    List<Order> findAllByStatus(Integer status);
}
