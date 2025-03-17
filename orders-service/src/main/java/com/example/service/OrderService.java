package com.example.service;

import com.example.dto.OrderDTO;
import com.example.model.Order;
import com.example.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setArticleNumbers(orderDTO.getArticleNumbers());
        order.setAmount(orderDTO.getAmount());
        order.setStatus("NEW");
        orderRepository.save(order);

        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus());

        kafkaTemplate.send("new_orders", String.valueOf(orderDTO.getId()), orderDTO);
        logger.info("Order created: {}", orderDTO);
        return orderDTO;
    }

    public OrderDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);

        OrderDTO orderDTO = order.convertToDTO();
        logger.info("Order status updated: {}", orderDTO);
        return orderDTO;
    }
}