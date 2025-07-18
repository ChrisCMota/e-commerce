package com.christian.ecommerce.mapper;

import com.christian.ecommerce.dto.OrderDTO;
import com.christian.ecommerce.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order orderDtoToOrder(OrderDTO orderDTO);

    OrderDTO orderToOrderDto(Order order);

    List<Order> orderDtoListToOrderList(List<OrderDTO> orderDTOS);

    List<OrderDTO> orderListToOrderDtoList(List<Order> orders);
}
