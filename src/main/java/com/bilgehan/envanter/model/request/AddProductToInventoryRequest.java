package com.bilgehan.envanter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddProductToInventoryRequest implements Serializable {
    private long productId;
    private long warehouseId;
    private long amount;
}