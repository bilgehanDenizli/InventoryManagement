package com.bilgehan.envanter.model.dto;

import com.bilgehan.envanter.model.entity.ProductCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private long id;
    private String name;
    private ProductCategory productCategory;
}
