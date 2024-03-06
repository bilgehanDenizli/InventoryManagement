package com.bilgehan.envanter.model.request;

import lombok.Data;

@Data
public class AddProductToInventoryRequest {
    private long productId;
    private long warehouseId;
    private long amount;
}