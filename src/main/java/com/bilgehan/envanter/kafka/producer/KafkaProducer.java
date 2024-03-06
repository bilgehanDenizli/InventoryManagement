package com.bilgehan.envanter.kafka.producer;

import com.bilgehan.envanter.model.entity.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void deleteCache(String cacheName) {
        kafkaTemplate.send("inventory_cache_delete", UUID.randomUUID().toString(),cacheName);
    }
}
