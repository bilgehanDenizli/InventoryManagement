package com.bilgehan.envanter.model.request;

import lombok.Data;

@Data
public class GetProductsRequest {
    private int page;
    private int limit;
}
