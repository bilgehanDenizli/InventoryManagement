package com.bilgehan.envanter.Repository;

import com.bilgehan.envanter.Model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
