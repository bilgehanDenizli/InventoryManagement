package com.bilgehan.envanter.model.request;

import lombok.Data;

@Data
public class DeleteFromInventoryRequest {
    private long productId;
    private long warehouseId;
}
