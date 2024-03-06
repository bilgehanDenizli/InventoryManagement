package com.bilgehan.envanter.Model.Dto;

import com.bilgehan.envanter.Model.entity.Product;
import com.bilgehan.envanter.Model.entity.Warehouse;
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
