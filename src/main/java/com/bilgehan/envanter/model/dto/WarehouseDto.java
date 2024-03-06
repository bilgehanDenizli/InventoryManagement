package com.bilgehan.envanter.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseDto {
    private long id;
    private String name;
    private String city;
    private String region;
}
