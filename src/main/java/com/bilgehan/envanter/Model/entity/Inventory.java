package com.bilgehan.envanter.Model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory")
@Where(clause = "is_deleted=false")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="warehouse_id", nullable=false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    private long amount;

    @CreationTimestamp
    private Timestamp updatedAt;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
}
