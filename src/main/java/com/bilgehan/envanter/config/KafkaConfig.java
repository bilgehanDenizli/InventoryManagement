package com.bilgehan.envanter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

   /* @Bean
    public NewTopic createTopic() {
        return new NewTopic("inventory-cache-delete", 5,(short) 1);
    }*/
}
