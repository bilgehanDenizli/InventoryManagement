package com.bilgehan.envanter.Model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Where(clause = "is_deleted=false")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

}
