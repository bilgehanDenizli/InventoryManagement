package com.bilgehan.envanter.kafka.consumer;

import com.bilgehan.envanter.model.kafka.WarehouseCache;
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

    @KafkaListener(topics = "inventory-cache-delete", groupId = "group-1")
    public void consumeDeleteCaches(List<String> cacheNames) {
        cacheNames.forEach(this::deleteCache);
    }

    @KafkaListener(topics = "inventory-cache-delete-by-name", groupId = "group-1")
    public void consumeDeleteCache(String cacheName) {
        deleteCache(cacheName);
    }

    @KafkaListener(topics = "inventory-cache-delete-by-warehouse-name", groupId = "group-1")
    public void consumeDeleteCacheByWarehouseName(WarehouseCache warehouseCache) {
        warehouseCache.getCacheName().forEach(cacheName -> {
            deleteCacheByWarehouseName(cacheName,warehouseCache.getWarehouseName());
        });
    }

    private void deleteCacheByWarehouseName(String cacheName, String warehouseName) {
        IMap<Integer, String> map = hazelcastInstance.getMap(cacheName);
        map.remove(warehouseName);
        log.info("Deleting cache name {} and warehouse name {}", cacheName,warehouseName);
    }

    public void deleteCache(String cacheName) {
        IMap<Integer, String> map = hazelcastInstance.getMap(cacheName);
        map.evictAll();
        log.info("Deleting cache name {}", cacheName);
    }
}
