package com.example.controller;

import com.example.dto.OrderDTO;
import com.example.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public OrderDTO placeOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @PutMapping("/order/{id}/status")
    public OrderDTO updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        return orderService.updateOrderStatus(id, status);
    }
}