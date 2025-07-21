package com.christian.ecommerce.controller;

import com.christian.ecommerce.dto.OrderDTO;
import com.christian.ecommerce.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("orders")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO){
        OrderDTO orderDTOCreated = orderService.addNewOrder(orderDTO);

        return ResponseEntity.ok(orderDTOCreated);
    }

    @GetMapping("orders")
    public ResponseEntity<List<OrderDTO>> getAll(){
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Integer id){
        OrderDTO orderByNumber = orderService.getByNumber(id);

        return ResponseEntity.ok(orderByNumber);
    }

    
}
