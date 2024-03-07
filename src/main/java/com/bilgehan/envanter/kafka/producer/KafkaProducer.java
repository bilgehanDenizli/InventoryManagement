package com.bilgehan.envanter.kafka.producer;

import com.bilgehan.envanter.model.entity.Inventory;
import com.bilgehan.envanter.util.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, List<String>> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplateString;

    public KafkaProducer(KafkaTemplate<String, List<String>> kafkaTemplate, KafkaTemplate<String, String> kafkaTemplateString) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateString = kafkaTemplateString;
    }

    public void deleteCache() {
        List<String> caches = CacheConstants.getCacheNames();
        kafkaTemplate.send("inventory-cache-delete", UUID.randomUUID().toString(),caches);
    }

    public void deleteCacheByName(String cacheName) {
        kafkaTemplateString.send("inventory-cache-delete-by-name", UUID.randomUUID().toString(),cacheName);
    }
}
