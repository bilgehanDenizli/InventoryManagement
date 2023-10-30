package com.bilgehan.envanter.Model.request;

import lombok.Data;

@Data
public class DeleteFromInventoryRequest {
    private long productId;
    private long warehouseId;
}
