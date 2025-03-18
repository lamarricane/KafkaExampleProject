package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaAdmin.NewTopics createTopics() {
        return new KafkaAdmin.NewTopics(
                new org.apache.kafka.clients.admin.NewTopic("web_logs", 3, (short) 1),
                new org.apache.kafka.clients.admin.NewTopic("new_orders", 3, (short) 1),
                new org.apache.kafka.clients.admin.NewTopic("payed_orders", 3, (short) 1),
                new org.apache.kafka.clients.admin.NewTopic("sent_orders", 3, (short) 1)
        );
    }
}