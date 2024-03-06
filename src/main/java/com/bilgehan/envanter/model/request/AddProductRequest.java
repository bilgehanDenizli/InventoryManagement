package com.bilgehan.envanter.model.request;

import com.bilgehan.envanter.model.entity.ProductCategory;
import lombok.Data;

@Data
public class AddProductRequest {
    private String name;
    private ProductCategory productCategory;
}
