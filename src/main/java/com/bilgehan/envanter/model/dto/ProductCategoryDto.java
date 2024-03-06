package com.bilgehan.envanter.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategoryDto {
    private long id;
    private String category;
}
