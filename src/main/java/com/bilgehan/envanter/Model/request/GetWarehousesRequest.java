package com.bilgehan.envanter.Model.request;

import lombok.Data;

@Data
public class GetWarehousesRequest {
    private int page;
    private int limit;
}
