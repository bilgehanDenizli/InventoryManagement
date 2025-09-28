package com.bilgehan.envanter.controller;

import com.bilgehan.envanter.kafka.consumer.KafkaConsumer;
import com.bilgehan.envanter.kafka.producer.KafkaProducer;
import com.bilgehan.envanter.model.request.CacheDeleteByNameRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class CacheController {
    private final KafkaProducer kafkaProducer;
    private final KafkaConsumer kafkaConsumer;

    public CacheController(KafkaProducer kafkaProducer, KafkaConsumer kafkaConsumer) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaConsumer = kafkaConsumer;
    }

    @PostMapping("/flushAll")
    public void flushAll() {
        kafkaProducer.deleteCache();
    }

    @PostMapping("/flushByName")
    public void flushByName(@RequestBody CacheDeleteByNameRequest request){
        kafkaProducer.deleteCacheByName(request.getCacheName());
    }

}
