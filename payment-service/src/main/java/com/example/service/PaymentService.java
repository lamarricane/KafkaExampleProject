package com.example.service;

import com.example.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public PaymentService(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "new_orders", groupId = "group_id")
    public void processOrder(OrderDTO order) {
        try {
            logger.info("Processing payment for order: {}", order.getId());
            if (Math.random() > 0.5) {
                throw new RuntimeException("Payment processing failed");
            }
            order.setStatus("PAYED");
            kafkaTemplate.send("payed_orders", order);
        } catch (Exception e) {
            logger.error("Error processing order: {}", order.getId(), e);
            kafkaTemplate.send("new_orders.DLT", order);
        }
    }
}