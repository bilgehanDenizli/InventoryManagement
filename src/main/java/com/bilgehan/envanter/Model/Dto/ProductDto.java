package com.bilgehan.envanter.Model.Dto;

import com.bilgehan.envanter.Model.entity.ProductCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private long id;
    private String name;
    private ProductCategory productCategory;
}
