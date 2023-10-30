package com.bilgehan.envanter.Service;

import com.bilgehan.envanter.Model.Dto.InventoryDto;
import com.bilgehan.envanter.Model.entity.Inventory;
import com.bilgehan.envanter.Model.entity.InventoryHistory;
import com.bilgehan.envanter.Model.entity.Product;
import com.bilgehan.envanter.Model.entity.Warehouse;
import com.bilgehan.envanter.Model.request.*;
import com.bilgehan.envanter.Repository.InventoryHistoryRepository;
import com.bilgehan.envanter.Repository.InventoryRepository;
import com.bilgehan.envanter.Repository.ProductRepository;
import com.bilgehan.envanter.Repository.WarehouseRepository;
import com.bilgehan.envanter.exception.NotAcceptableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class InventoryService {

    Logger logger = LoggerFactory.getLogger(InventoryService.class);
    private final InventoryRepository inventoryRepository;
    private final InventoryHistoryRepository inventoryHistoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public InventoryService(InventoryRepository inventoryRepository, InventoryHistoryRepository inventoryHistoryRepository, ProductRepository productRepository, WarehouseRepository warehouseRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryHistoryRepository = inventoryHistoryRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }


    public void addProduct(AddProductToInventoryRequest request) {

        if (request.getAmount() == 0) throw new NotAcceptableException("You cant add or take zero products.");

        //checks if product exists within the warehouse inventory
        boolean inventoryFlag = inventoryRepository.existsByWarehouse_IdAndProduct_Id(request.getWarehouseId(), request.getProductId());
        Inventory inventory;

        if (inventoryFlag) {
            //if inventory exist adds or subtracts the amount
            inventory = inventoryRepository.getByWarehouse_IdAndProduct_Id(request.getWarehouseId(), request.getProductId());
            if (inventory.getAmount() + request.getAmount() < 0)
                throw new NotAcceptableException("You cant take more than you have in the warehouse inventory.");
            inventory.setAmount(inventory.getAmount() + request.getAmount());

            //if products being taken away and less than 10 products left send warn log.
            if (inventory.getAmount() + request.getAmount() < 10 && request.getAmount() < 0) {
                logger.warn("You have less than ten products left in your inventory.");
            }
        } else {
            //if product doesn't exist in the inventory and the given amount is smaller than zero throws an exception.
            if (request.getAmount() < 0)
                throw new NotAcceptableException("You cant take products you dont have in the warehouse inventory.");

            //gets data needed to build inventory object from database
            Product product = productRepository.getProductById(request.getProductId());
            Warehouse warehouse = warehouseRepository.getWarehouseById(request.getWarehouseId());

            inventory = Inventory.builder()
                    .product(product)
                    .warehouse(warehouse)
                    .amount(request.getAmount())
                    .build();
        }
        //inventory insert
        inventoryRepository.save(inventory);

        //inventory history build and insert
        InventoryHistory inventoryHistory = InventoryHistory.builder()
                .productId(inventory.getProduct().getId())
                .amountChange(request.getAmount())
                .warehouseId(request.getWarehouseId())
                .build();

        inventoryHistoryRepository.save(inventoryHistory);
    }

    public void deleteProduct(DeleteFromInventoryRequest request) {
        Inventory inventory = inventoryRepository.getInventoryByProduct_IdAndWarehouse_Id(request.getProductId(), request.getWarehouseId());
        if (inventory == null) throw new NotAcceptableException("Product not found.");
        inventory.setDeleted(true);
        inventoryRepository.save(inventory);
    }

    public Set<InventoryDto> getByWarehouseName(GetInvByWarehouseNameRequest request) {
        if (request.getWarehouseName() == null || request.getWarehouseName().trim().equals(""))
            throw new NotAcceptableException("Warehouse name cannot be empty.");

        Set<Inventory> inventorySet = inventoryRepository.getInventoryByWarehouse_Name(request.getWarehouseName());

        if (inventorySet == null || inventorySet.size() == 0)
            throw new NotAcceptableException("Warehouse inventory by that name not found.");

        return mapInventoryDtos(inventorySet);
    }

    public Set<InventoryDto> getByWarehouseCity(GetInvByWarehouseCityRequest request) {
        if (request.getCity() == null || request.getCity().trim().equals(""))
            throw new NotAcceptableException("Warehouse city cannot be empty.");

        Set<Inventory> inventorySet = inventoryRepository.getInventoryByWarehouse_City(request.getCity());

        if (inventorySet == null || inventorySet.size() == 0)
            throw new NotAcceptableException("Warehouse inventory in that city not found.");

        return mapInventoryDtos(inventorySet);
    }

    public Set<InventoryDto> getByWarehouseRegion(GetInvByWarehouseRegionRequest request) {
        if (request.getRegion() == null || request.getRegion().trim().equals(""))
            throw new NotAcceptableException("Warehouse region cannot be empty.");

        Set<Inventory> inventorySet = inventoryRepository.getInventoryByWarehouse_Region(request.getRegion());

        if (inventorySet == null || inventorySet.size() == 0)
            throw new NotAcceptableException("Warehouse inventory in that region not found.");

        return mapInventoryDtos(inventorySet);
    }

    public Set<InventoryDto> getByProductCategory(GetInvByProductCategoryRequest request) {
        if (request.getCategory() == null || request.getCategory().trim().equals(""))
            throw new NotAcceptableException("Product category cannot be empty.");

        Set<Inventory> inventorySet = inventoryRepository.getInventoryByProduct_ProductCategory_Category(request.getCategory());

        if (inventorySet == null || inventorySet.size() == 0)
            throw new NotAcceptableException("Inventory with the given product category not found.");

        return mapInventoryDtos(inventorySet);
    }

    public Set<InventoryDto> getByProductId(GetInvByProductIdRequest request) {
        Set<Inventory> inventorySet = inventoryRepository.getInventoryByProduct_Id(request.getProductId());

        if (inventorySet == null || inventorySet.size() == 0)
            throw new NotAcceptableException("Inventory with the given product ID not found.");

        return mapInventoryDtos(inventorySet);
    }

    public Set<InventoryDto> getByProductName(GetInvByProductNameRequest request) {
        if (request.getProductName() == null || request.getProductName().trim().equals(""))
            throw new NotAcceptableException("Product name cannot be empty.");

        Set<Inventory> inventorySet = inventoryRepository.getInventoryByProduct_Name(request.getProductName());

        if (inventorySet == null || inventorySet.size() == 0)
            throw new NotAcceptableException("Inventory with the given product name not found.");

        return mapInventoryDtos(inventorySet);
    }

    public Set<InventoryDto> mapInventoryDtos(Set<Inventory> inventorySet) {
        Set<InventoryDto> inventoryDtoSet = new HashSet<>();
        for (Inventory inventory : inventorySet
        ) {
            inventoryDtoSet.add(InventoryDto.builder()
                    .inventoryId(inventory.getId())
                    .product(inventory.getProduct())
                    .warehouse(inventory.getWarehouse())
                    .amount(inventory.getAmount())
                    .isDeleted(inventory.isDeleted())
                    .updatedAt(inventory.getUpdatedAt())
                    .build());
        }
        return inventoryDtoSet;
    }

}
