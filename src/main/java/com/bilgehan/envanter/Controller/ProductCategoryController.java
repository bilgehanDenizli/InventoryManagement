package com.bilgehan.envanter.Controller;

import com.bilgehan.envanter.Model.Dto.ProductCategoryDto;
import com.bilgehan.envanter.Service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/productCategory")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<List<ProductCategoryDto>> getCategories() {
        return ResponseEntity.ok(productCategoryService.getCategories());
    }

}
