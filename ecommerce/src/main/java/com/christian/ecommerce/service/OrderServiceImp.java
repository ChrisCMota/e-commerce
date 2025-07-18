package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.OrderDAO;
import com.christian.ecommerce.dto.OrderDTO;
import com.christian.ecommerce.mapper.OrderMapper;
import com.christian.ecommerce.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements IOrderService{

    private OrderDAO repository;
    private OrderMapper orderMapper;

    public OrderServiceImp(OrderDAO repository, OrderMapper orderMapper){
        this.orderMapper = orderMapper;
        this.repository = repository;
    }

    @Override
    public OrderDTO addNewOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order order = orderMapper.orderDtoToOrder(orderDTO);

        OrderDTO orderDTOSaved = orderMapper.orderToOrderDto(repository.save(order));

        return orderDTOSaved;
    }

    @Override
    public List<OrderDTO> getAll() {
        List<OrderDTO> orderDTOSList = orderMapper.orderListToOrderDtoList((List<Order>) repository.findAll());

        return orderDTOSList;
    }

    @Override
    public OrderDTO getByNumber(Integer number) {
        return null;
    }

    @Override
    public List<OrderDTO> getByStatus(Integer status) {
        return List.of();
    }
}
