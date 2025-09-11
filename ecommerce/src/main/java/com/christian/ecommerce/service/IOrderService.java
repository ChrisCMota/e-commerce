package com.christian.ecommerce.service;

import com.christian.ecommerce.dto.MonthlyRevenue;
import com.christian.ecommerce.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    OrderDTO addNewOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(OrderDTO orderDTO);
    List<OrderDTO> getAll();
    OrderDTO getByNumber(Integer number);
    List<OrderDTO> getByStatus(Integer status);
    List<MonthlyRevenue> getRevenue(Integer year);
}
