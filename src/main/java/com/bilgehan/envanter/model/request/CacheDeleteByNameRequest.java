package com.bilgehan.envanter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CacheDeleteByNameRequest implements Serializable {
    private String cacheName;
}