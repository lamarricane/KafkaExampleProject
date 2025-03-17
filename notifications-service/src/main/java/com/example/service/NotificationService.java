package com.example.service;

import com.example.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @KafkaListener(topics = "sent_orders", groupId = "group_id")
    public void processShipping(OrderDTO order) {
        try {
            logger.info("Sending notification for order: {}", order.getId());
            if (Math.random() > 0.5) {
                throw new RuntimeException("Notification sending failed");
            }
            logger.info("Notification sent for order: {}", order.getId());
        } catch (Exception e) {
            logger.error("Error sending notification for order: {}", order.getId(), e);
        }
    }
}