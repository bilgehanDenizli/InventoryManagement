package com.bilgehan.envanter.Model.request;

import lombok.Data;

@Data
public class AddProductToInventoryRequest {
    private long productId;
    private long warehouseId;
    private long amount;
}