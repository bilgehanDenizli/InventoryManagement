package com.bilgehan.envanter.Model.request;

import lombok.Data;

@Data
public class GetProductsRequest {
    private int page;
    private int limit;
}
