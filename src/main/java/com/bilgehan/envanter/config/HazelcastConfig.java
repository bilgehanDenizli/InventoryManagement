package com.bilgehan.envanter.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
    @Bean
    public void config() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("Inventory_Management");

        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<String,String> map = client.getMap("my-map");
        map.put("1", "John");
        map.put("2", "Mary");
        map.put("3", "Jane");
    }

}
