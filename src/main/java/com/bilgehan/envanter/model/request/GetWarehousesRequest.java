package com.bilgehan.envanter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetWarehousesRequest implements Serializable {
    private int page;
    private int limit;
}
