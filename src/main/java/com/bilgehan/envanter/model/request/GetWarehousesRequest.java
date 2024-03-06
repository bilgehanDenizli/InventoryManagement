package com.bilgehan.envanter.model.request;

import lombok.Data;

@Data
public class GetWarehousesRequest {
    private int page;
    private int limit;
}
