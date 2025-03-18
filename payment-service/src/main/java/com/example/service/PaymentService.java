package com.example.service;

import com.example.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public PaymentService(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "new_orders", groupId = "group_id")
    public void processOrder(@Payload OrderDTO order,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                             @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        logger.info("Received order: {} from partition: {} with key: {}", order, partition, key);
        try {
            logger.info("Processing payment for order: {}", order.getId());
            order.setStatus("PAYED");
            kafkaTemplate.send("payed_orders", String.valueOf(order.getId()), order);
        } catch (Exception e) {
            logger.error("Error processing order: {}", order.getId(), e);
            kafkaTemplate.send("new_orders.DLT", order);
        }
    }
}