package com.bilgehan.envanter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInvByWarehouseNameRequest implements Serializable {
    private String warehouseName;
}
