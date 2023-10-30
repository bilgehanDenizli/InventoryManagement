package com.bilgehan.envanter.Model.Dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class InventoryHistoryDto {
    private long id;
    private long productId;
    private long warehouseId;
    private long amountChange;
    private Timestamp createdAt;
}
