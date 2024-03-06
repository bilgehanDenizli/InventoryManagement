package com.bilgehan.envanter.model.dto;

import com.bilgehan.envanter.model.entity.Product;
import com.bilgehan.envanter.model.entity.Warehouse;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class InventoryDto {
    private Long inventoryId;
    private long amount;
    private Product product;
    private Warehouse warehouse;
    private Timestamp updatedAt;
    private boolean isDeleted;
}
