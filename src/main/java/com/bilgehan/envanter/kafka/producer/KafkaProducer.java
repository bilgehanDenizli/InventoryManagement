package com.bilgehan.envanter.kafka.producer;

import com.bilgehan.envanter.model.dto.WarehouseDto;
import com.bilgehan.envanter.model.entity.Inventory;
import com.bilgehan.envanter.model.entity.Warehouse;
import com.bilgehan.envanter.model.kafka.WarehouseCache;
import com.bilgehan.envanter.service.WarehouseService;
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
    private final KafkaTemplate<String, WarehouseCache> kafkaTemplateWarehouseCache;
    private final WarehouseService warehouseService;

    public KafkaProducer(KafkaTemplate<String, List<String>> kafkaTemplate, KafkaTemplate<String, String> kafkaTemplateString, KafkaTemplate<String, WarehouseCache> kafkaTemplateWarehouseCache, WarehouseService warehouseService) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplateString = kafkaTemplateString;
        this.kafkaTemplateWarehouseCache = kafkaTemplateWarehouseCache;
        this.warehouseService = warehouseService;
    }

    public void deleteCache() {
        List<String> caches = CacheConstants.getCacheNames();
        kafkaTemplate.send("inventory-cache-delete", UUID.randomUUID().toString(),caches);
    }

    public void deleteCacheByName(String cacheName) {
        kafkaTemplateString.send("inventory-cache-delete-by-name", UUID.randomUUID().toString(),cacheName);
    }

    public void deleteCacheByWarehouseName(long warehouseId) {
        WarehouseDto warehouse = warehouseService.getWarehouseById(warehouseId);
        List<String> caches = CacheConstants.getCacheNames();
        WarehouseCache warehouseCache = WarehouseCache.builder()
                .warehouseName(warehouse.getName())
                .cacheName(caches)
                .build();
        kafkaTemplateWarehouseCache.send("inventory-cache-delete-by-warehouse-name", UUID.randomUUID().toString(),warehouseCache);
    }
}
