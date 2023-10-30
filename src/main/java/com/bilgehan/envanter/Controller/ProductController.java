package com.bilgehan.envanter.Controller;

import com.bilgehan.envanter.Model.Dto.ProductDto;
import com.bilgehan.envanter.Model.request.AddProductRequest;
import com.bilgehan.envanter.Model.request.GetProductByIdRequest;
import com.bilgehan.envanter.Model.request.UpdateProductRequest;
import com.bilgehan.envanter.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody AddProductRequest request){
        productService.addProduct(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/byId")
    public ResponseEntity<ProductDto> getProductById(@RequestBody GetProductByIdRequest request){
        return ResponseEntity.ok(productService.getProductById(request.getId()));
    }

    @PostMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Void> updateProduct(@RequestBody UpdateProductRequest request){
        productService.updateProduct(request);
        return ResponseEntity.ok().build();
    }
}
