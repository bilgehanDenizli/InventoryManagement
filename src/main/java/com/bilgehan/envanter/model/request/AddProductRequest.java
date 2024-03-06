package com.bilgehan.envanter.Model.request;

import com.bilgehan.envanter.Model.entity.ProductCategory;
import lombok.Data;

@Data
public class AddProductRequest {
    private String name;
    private ProductCategory productCategory;
}
