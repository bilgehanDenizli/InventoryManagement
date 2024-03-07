package com.bilgehan.envanter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetInvByWarehouseRegionRequest implements Serializable {
    private String region;
}
