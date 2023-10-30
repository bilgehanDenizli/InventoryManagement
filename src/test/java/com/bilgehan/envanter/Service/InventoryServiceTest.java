package com.bilgehan.envanter.Service;

import com.bilgehan.envanter.Model.Dto.InventoryDto;
import com.bilgehan.envanter.Model.entity.Inventory;
import com.bilgehan.envanter.Model.entity.Product;
import com.bilgehan.envanter.Model.entity.ProductCategory;
import com.bilgehan.envanter.Model.entity.Warehouse;
import com.bilgehan.envanter.Model.request.*;
import com.bilgehan.envanter.Repository.InventoryHistoryRepository;
import com.bilgehan.envanter.Repository.InventoryRepository;
import com.bilgehan.envanter.Repository.ProductRepository;
import com.bilgehan.envanter.Repository.WarehouseRepository;
import com.bilgehan.envanter.exception.NotAcceptableException;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class InventoryServiceTest {

    private InventoryService inventoryService;

    private InventoryRepository inventoryRepository;
    private InventoryHistoryRepository inventoryHistoryRepository;
    private ProductRepository productRepository;
    private WarehouseRepository warehouseRepository;

    @Before
    public void setUp() throws Exception {
        inventoryRepository = Mockito.mock(InventoryRepository.class);
        inventoryHistoryRepository = Mockito.mock(InventoryHistoryRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        warehouseRepository = Mockito.mock(WarehouseRepository.class);

        inventoryService = new InventoryService(
                inventoryRepository,
                inventoryHistoryRepository,
                productRepository,
                warehouseRepository);
    }

    @Test(expected = NotAcceptableException.class)
    public void whenAddProductCalledWithZeroAmount_itShouldThrowNotAcceptableException() {
        AddProductToInventoryRequest request = new AddProductToInventoryRequest();
        request.setAmount(0);
        request.setProductId(1);
        request.setWarehouseId(1);

        inventoryService.addProduct(request);
    }

    @Test(expected = NotAcceptableException.class)
    public void whenAddProductCalledWithMinusAmountToTakeFromInventoryButTheAmountIsLowerOnDatabase_ItShouldThrowNotAcceptableException() {
        AddProductToInventoryRequest request = new AddProductToInventoryRequest();
        request.setAmount(-5);
        request.setProductId(1);
        request.setWarehouseId(1);

        Inventory inventory = generateInventory();
        Mockito.when(inventoryRepository.existsByWarehouse_IdAndProduct_Id(request.getWarehouseId(), request.getProductId())).thenReturn(true);
        Mockito.when(inventoryRepository.getByWarehouse_IdAndProduct_Id(request.getWarehouseId(), request.getProductId())).thenReturn(inventory);

        inventoryService.addProduct(request);
    }

    @Test(expected = NotAcceptableException.class)
    public void whenAddProductCalledToMakeNewInventoryButTheAmountLessThanZero_ItShouldThrowNotAcceptableException() {
        AddProductToInventoryRequest request = new AddProductToInventoryRequest();
        request.setAmount(-5);
        request.setProductId(2);
        request.setWarehouseId(1);

        Inventory inventory = generateInventory();
        Product product = generateProduct();
        Warehouse warehouse = generateWarehouse();

        Mockito.when(inventoryRepository.existsByWarehouse_IdAndProduct_Id(request.getWarehouseId(), request.getProductId())).thenReturn(false);
        Mockito.when(inventoryRepository.getByWarehouse_IdAndProduct_Id(request.getWarehouseId(), request.getProductId())).thenReturn(inventory);
        Mockito.when(productRepository.getProductById(request.getProductId())).thenReturn(product);
        Mockito.when(warehouseRepository.getWarehouseById(request.getWarehouseId())).thenReturn(warehouse);

        inventoryService.addProduct(request);
    }

    @Test
    public void whenGetByWarehouseNameCalledWithValidRequest_ItShouldReturnValidInventoryDtoSet() {
        GetInvByWarehouseNameRequest request = new GetInvByWarehouseNameRequest();
        request.setWarehouseName("warehouse a");

        Set<Inventory> inventorySet = new HashSet<>();
        inventorySet.add(generateInventory());

        Mockito.when(inventoryRepository.getInventoryByWarehouse_Name(request.getWarehouseName())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByWarehouseName(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByWarehouse_Name(request.getWarehouseName());
    }

    @Test(expected = NotAcceptableException.class)
    public void whenGetByWarehouseNameCalledWithNonExistingName_ItShouldThrowNotAcceptableException() {
        GetInvByWarehouseNameRequest request = new GetInvByWarehouseNameRequest();
        request.setWarehouseName("warehouse f");

        Set<Inventory> inventorySet = new HashSet<>();

        Mockito.when(inventoryRepository.getInventoryByWarehouse_Name(request.getWarehouseName())).thenReturn(inventorySet);
        Mockito.when(inventoryRepository.getInventoryByWarehouse_Name(request.getWarehouseName())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        //test should stop after this
        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByWarehouseName(request);

        Assert.assertNotEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByWarehouse_Name(request.getWarehouseName());
    }

    @Test
    public void whenGetByWarehouseCityCalledWithValidRequest_ItShouldReturnValidInventoryDtoSet() {
        GetInvByWarehouseCityRequest request = new GetInvByWarehouseCityRequest();
        request.setCity("antalya");

        Set<Inventory> inventorySet = new HashSet<>();
        inventorySet.add(generateInventory());

        Mockito.when(inventoryRepository.getInventoryByWarehouse_City(request.getCity())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByWarehouseCity(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByWarehouse_City(request.getCity());
    }

    @Test(expected = NotAcceptableException.class)
    public void whenGetByWarehouseCityCalledWithNonExistingCity_ItShouldThrowNotAcceptableException() {
        GetInvByWarehouseCityRequest request = new GetInvByWarehouseCityRequest();
        request.setCity("");

        Set<Inventory> inventorySet = new HashSet<>();

        Mockito.when(inventoryRepository.getInventoryByWarehouse_City(request.getCity())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        //test should stop after this
        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByWarehouseCity(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByWarehouse_City(request.getCity());
    }

    @Test
    public void whenGetByWarehouseRegionCalledWithValidRequest_ItShouldReturnValidInventoryDtoSet() {
        GetInvByWarehouseRegionRequest request = new GetInvByWarehouseRegionRequest();
        request.setRegion("akdeniz");

        Set<Inventory> inventorySet = new HashSet<>();
        inventorySet.add(generateInventory());

        Mockito.when(inventoryRepository.getInventoryByWarehouse_Region(request.getRegion())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByWarehouseRegion(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByWarehouse_Region(request.getRegion());
    }

    @Test(expected = NotAcceptableException.class)
    public void whenGetByWarehouseRegionCalledWithNonExistingRegion_ItShouldThrowNotAcceptableException() {
        GetInvByWarehouseRegionRequest request = new GetInvByWarehouseRegionRequest();
        request.setRegion(" ");

        Set<Inventory> inventorySet = new HashSet<>();

        Mockito.when(inventoryRepository.getInventoryByWarehouse_Region(request.getRegion())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        //test should stop after this
        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByWarehouseRegion(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByWarehouse_Region(request.getRegion());
    }

    @Test
    public void whenGetByProductCategoryCalledWithValidRequest_ItShouldReturnValidInventoryDtoSet() {
        GetInvByProductCategoryRequest request = new GetInvByProductCategoryRequest();
        request.setCategory("kitap");

        Set<Inventory> inventorySet = new HashSet<>();
        inventorySet.add(generateInventory());

        Mockito.when(inventoryRepository.getInventoryByProduct_ProductCategory_Category(request.getCategory())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByProductCategory(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByProduct_ProductCategory_Category(request.getCategory());
    }

    @Test(expected = NotAcceptableException.class)
    public void whenGetByProductCategoryCalledWithNonExistingCategory_ItShouldThrowNotAcceptableException() {
        GetInvByProductCategoryRequest request = new GetInvByProductCategoryRequest();
        request.setCategory(" ");

        Set<Inventory> inventorySet = new HashSet<>();

        Mockito.when(inventoryRepository.getInventoryByProduct_Name(request.getCategory())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        //test should stop after this
        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByProductCategory(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByProduct_Name(request.getCategory());
    }

    @Test
    public void whenGetByProductIdCalledWithValidRequest_ItShouldReturnValidInventoryDtoSet() {
        GetInvByProductIdRequest request = new GetInvByProductIdRequest();
        request.setProductId(1);

        Set<Inventory> inventorySet = new HashSet<>();
        inventorySet.add(generateInventory());

        Mockito.when(inventoryRepository.getInventoryByProduct_Id(request.getProductId())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByProductId(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByProduct_Id(request.getProductId());
    }

    @Test(expected = NotAcceptableException.class)
    public void whenGetByProductIdCalledWithNonExistingId_ItShouldThrowNotAcceptableException() {
        GetInvByProductIdRequest request = new GetInvByProductIdRequest();
        request.setProductId(100);

        Set<Inventory> inventorySet = new HashSet<>();

        Mockito.when(inventoryRepository.getInventoryByProduct_Id(request.getProductId())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        //test should stop after this
        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByProductId(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByProduct_Id(request.getProductId());
    }

    @Test
    public void whenGetByProductNameCalledWithValidRequest_ItShouldReturnValidInventoryDtoSet() {
        GetInvByProductNameRequest request = new GetInvByProductNameRequest();
        request.setProductName("Roman 1");

        Set<Inventory> inventorySet = new HashSet<>();
        inventorySet.add(generateInventory());

        Mockito.when(inventoryRepository.getInventoryByProduct_Name(request.getProductName())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByProductName(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByProduct_Name(request.getProductName());
    }

    @Test(expected = NotAcceptableException.class)
    public void whenGetByProductNameCalledWithNonExistingName_ItShouldThrowNotAcceptableException() {
        GetInvByProductNameRequest request = new GetInvByProductNameRequest();
        request.setProductName(" ");

        Set<Inventory> inventorySet = new HashSet<>();

        Mockito.when(inventoryRepository.getInventoryByProduct_Name(request.getProductName())).thenReturn(inventorySet);
        Set<InventoryDto> inventoryDtos = inventoryService.mapInventoryDtos(inventorySet);

        //test should stop after this
        Set<InventoryDto> inventoryDtoSetResult = inventoryService.getByProductName(request);

        Assert.assertEquals(inventoryDtoSetResult, inventoryDtos);

        Mockito.verify(inventoryRepository).getInventoryByProduct_Name(request.getProductName());
    }

    private Warehouse generateWarehouse() {
        return Warehouse.builder()
                .id(1)
                .name("warehouse a")
                .city("antalya")
                .region("akdeniz")
                .build();
    }

    private Product generateProduct() {
        ProductCategory productCategory = ProductCategory.builder()
                .id(3)
                .category("Kağıt")
                .build();
        return Product.builder()
                .id(2L)
                .name("A4 Kağıt")
                .productCategory(productCategory)
                .isDeleted(false)
                .build();
    }

    private Inventory generateInventory() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ProductCategory productCategory = ProductCategory.builder()
                .id(1)
                .category("Kitap")
                .build();
        Product product = Product.builder()
                .id(1L)
                .name("Roman 1")
                .productCategory(productCategory)
                .isDeleted(false)
                .build();

        Warehouse warehouse = generateWarehouse();

        return Inventory.builder()
                .id(1L)
                .amount(4)
                .product(product)
                .warehouse(warehouse)
                .isDeleted(false)
                .updatedAt(timestamp)
                .build();
    }
}