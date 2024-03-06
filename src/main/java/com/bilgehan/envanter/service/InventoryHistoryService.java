package com.bilgehan.envanter.service;

import com.bilgehan.envanter.model.dto.InventoryHistoryDto;
import com.bilgehan.envanter.model.entity.InventoryHistory;
import com.bilgehan.envanter.model.request.GetHistoryRequest;
import com.bilgehan.envanter.repository.InventoryHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryHistoryService {
    private final InventoryHistoryRepository inventoryHistoryRepository;

    public InventoryHistoryService(InventoryHistoryRepository inventoryHistoryRepository) {
        this.inventoryHistoryRepository = inventoryHistoryRepository;
    }

    public List<InventoryHistoryDto> getHistory(GetHistoryRequest request) {

        List<InventoryHistoryDto> inventoryHistoryDtoList = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(request.getPage(),request.getLimit());
        Page<InventoryHistory> inventoryHistoryList = inventoryHistoryRepository.findAllWithPagingOrderByCreatedAtDesc(pageRequest);

        for (InventoryHistory inventoryHistory: inventoryHistoryList
        ) {
            InventoryHistoryDto inventoryHistoryDto = InventoryHistoryDto.builder()
                    .id(inventoryHistory.getId())
                    .productId(inventoryHistory.getProductId())
                    .warehouseId(inventoryHistory.getWarehouseId())
                    .amountChange(inventoryHistory.getAmountChange())
                    .createdAt(inventoryHistory.getCreatedAt())
                    .build();
            inventoryHistoryDtoList.add(inventoryHistoryDto);
        }
        return inventoryHistoryDtoList;
    }
}
