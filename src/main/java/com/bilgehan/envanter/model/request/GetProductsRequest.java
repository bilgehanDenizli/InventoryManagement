package com.bilgehan.envanter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetProductsRequest implements Serializable {
    private int page;
    private int limit;
}
