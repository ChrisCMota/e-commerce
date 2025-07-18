package com.christian.ecommerce.service;

import com.christian.ecommerce.dto.OrderDTO;

public interface IOrderService {
    OrderDTO addNewOrder(OrderDTO orderDTO);
}
