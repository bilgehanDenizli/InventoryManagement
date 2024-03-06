package com.bilgehan.envanter.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = "inventory_cache_delete",groupId = "group-1")
    public void consumeDeleteCache(String cacheName) {
        log.info("Deleting cache name {}", cacheName);

    }
}
