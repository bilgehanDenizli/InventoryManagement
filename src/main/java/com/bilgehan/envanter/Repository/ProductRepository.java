package com.bilgehan.envanter.Repository;

import com.bilgehan.envanter.Model.entity.Product;

import com.bilgehan.envanter.Model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductById(long productId);
    Set<Product> getAllByProductCategory(ProductCategory productCategory);
    boolean existsProductByName(String name);

}
