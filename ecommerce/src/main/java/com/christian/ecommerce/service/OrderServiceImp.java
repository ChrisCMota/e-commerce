package com.christian.ecommerce.service;

import com.christian.ecommerce.dao.OrderDAO;
import com.christian.ecommerce.dto.OrderDTO;
import com.christian.ecommerce.mapper.OrderMapper;
import com.christian.ecommerce.model.ItemOrder;
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
        Order order = orderMapper.orderDtoToOrder(orderDTO);

        order.getCustomer().setId(orderDTO.getCustomer().getId());

        //need to associate each item to the correspondent order
        for (ItemOrder item: order.getItems()){
            item.setOrder(order);
        }

        //could have a discount business logic here

        return orderMapper.orderToOrderDto(repository.save(order));
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
