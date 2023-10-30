package com.bilgehan.envanter.Model.request;

import com.bilgehan.envanter.Model.entity.ProductCategory;
import lombok.Data;

@Data
public class UpdateProductRequest {
    private long id;
    private String name;
    private ProductCategory productCategory;
    private boolean isDeleted;
}
