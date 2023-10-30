package com.bilgehan.envanter.Service;

import com.bilgehan.envanter.Model.Dto.ProductDto;
import com.bilgehan.envanter.Model.entity.Product;
import com.bilgehan.envanter.Model.request.UpdateProductRequest;
import com.bilgehan.envanter.Repository.ProductRepository;
import com.bilgehan.envanter.Model.request.AddProductRequest;
import com.bilgehan.envanter.exception.NotAcceptableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public ProductDto getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) throw new NotAcceptableException("Product not found.");

        return ProductDto.builder()
                .id(product.get().getId())
                .name(product.get().getName())
                .productCategory(product.get().getProductCategory())
                .build();
    }

    public List<ProductDto> getProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product: productList
             ) {
            productDtoList.add(
                    ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .productCategory(product.getProductCategory())
                    .build()
            );
        }
        return productDtoList;
    }

    public void updateProduct(UpdateProductRequest request) {
        Product product = productRepository.getProductById(request.getId());
        if (request.getName() != null) product.setName(request.getName());
        if (request.getProductCategory() != null) product.setProductCategory(request.getProductCategory());
        productRepository.save(product);
    }

    public void addProduct(AddProductRequest request) {
        checkProduct(request.getName());

        Product product = Product.builder()
                .name(request.getName())
                .productCategory(request.getProductCategory())
                .build();
        productRepository.save(product);
    }

    private void checkProduct(String name) {
        boolean flag = productRepository.existsProductByName(name);
        if (flag) throw new NotAcceptableException("Product with this name already exists.");
    }
}
