package com.bilgehan.envanter.repository;

import com.bilgehan.envanter.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

    boolean existsByWarehouse_IdAndProduct_Id(long warehouseId,long productId);
    Inventory getByWarehouse_IdAndProduct_Id(long warehouseId,long productId);
    Inventory getInventoryByProduct_IdAndWarehouse_Id(long productId,long warehouseId);
    Set<Inventory> getInventoryByWarehouse_Name(String name);
    Set<Inventory> getInventoryByWarehouse_City(String city);
    Set<Inventory> getInventoryByWarehouse_Region(String region);
    Set<Inventory> getInventoryByProduct_ProductCategory_Category(String category);
    Set<Inventory> getInventoryByProduct_Id(long productId);
    Set<Inventory> getInventoryByProduct_Name(String productName);
}
