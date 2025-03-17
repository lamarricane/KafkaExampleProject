package com.example.service;

import com.example.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private static final Logger logger = LoggerFactory.getLogger(ShippingService.class);

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public ShippingService(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "payed_orders", groupId = "group_id")
    public void processPayment(OrderDTO order) {
        try {
            logger.info("Shipping order: {}", order.getId());
            if (Math.random() > 0.5) {
                throw new RuntimeException("Shipping failed");
            }
            order.setStatus("SHIPPED");
            kafkaTemplate.send("sent_orders", order);
        } catch (Exception e) {
            logger.error("Error shipping order: {}", order.getId(), e);
            kafkaTemplate.send("payed_orders.DLT", order);
        }
    }
}