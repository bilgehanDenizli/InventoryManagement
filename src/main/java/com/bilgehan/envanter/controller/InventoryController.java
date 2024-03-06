package com.bilgehan.envanter.controller;

import com.bilgehan.envanter.model.dto.InventoryDto;
import com.bilgehan.envanter.model.request.*;
import com.bilgehan.envanter.service.InventoryService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/addOrTakeProduct")
    public ResponseEntity<Void> addProduct(@RequestBody AddProductToInventoryRequest request) {
        inventoryService.addProduct(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteProduct")
    public ResponseEntity<Void> deleteProduct(@RequestBody DeleteFromInventoryRequest request) {
        inventoryService.deleteProduct(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/byWarehouseName")
    @Cacheable("InventoryByWarehouseName")
    public ResponseEntity<Set<InventoryDto>> getByWarehouseName(@RequestBody GetInvByWarehouseNameRequest request){
        return ResponseEntity.ok(inventoryService.getByWarehouseName(request));
    }

    @PostMapping("/byWarehouseCity")
    @Cacheable("InventoryByWarehouseCity")
    public ResponseEntity<Set<InventoryDto>> getByWarehouseCity(@RequestBody GetInvByWarehouseCityRequest request){
        return ResponseEntity.ok(inventoryService.getByWarehouseCity(request));
    }

    @PostMapping("/byWarehouseRegion")
    @Cacheable("InventoryByWarehouseRegion")
    public ResponseEntity<Set<InventoryDto>> getByWarehouseRegion(@RequestBody GetInvByWarehouseRegionRequest request){
        return ResponseEntity.ok(inventoryService.getByWarehouseRegion(request));
    }

    @PostMapping("/byProductCategory")
    @Cacheable("InventoryByProductCategory")
    public ResponseEntity<Set<InventoryDto>> getByProductCategory(@RequestBody GetInvByProductCategoryRequest request){
        return ResponseEntity.ok(inventoryService.getByProductCategory(request));
    }

    @PostMapping("/byProductId")
    @Cacheable("InventoryByProductId")
    public ResponseEntity<Set<InventoryDto>> getByProductId(@RequestBody GetInvByProductIdRequest request){
        return ResponseEntity.ok(inventoryService.getByProductId(request));
    }

    @PostMapping("/byProductName")
    @Cacheable("InventoryByProductName")
    public ResponseEntity<Set<InventoryDto>> getByProductName(@RequestBody GetInvByProductNameRequest request){
        return ResponseEntity.ok(inventoryService.getByProductName(request));
    }
}
