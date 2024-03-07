package com.bilgehan.envanter.kafka.consumer;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaConsumer {


    private final HazelcastInstance hazelcastInstance;

    public KafkaConsumer(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @KafkaListener(topics = "inventory-cache-delete1", groupId = "group-1")
    public void consumeDeleteCaches(List<String> cacheNames) {
        cacheNames.forEach(this::deleteCache);
    }

    @KafkaListener(topics = "inventory-cache-delete-by-name1", groupId = "group-1")
    public void consumeDeleteCache(String cacheName) {
        deleteCache(cacheName);
    }

    public void deleteCache(String cacheName) {
        IMap<Integer, String> distributedMap = hazelcastInstance.getMap(cacheName);
        distributedMap.evictAll();
        log.info("Deleting cache name {}", cacheName);
    }
}
