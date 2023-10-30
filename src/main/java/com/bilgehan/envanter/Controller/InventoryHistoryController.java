package com.bilgehan.envanter.Controller;

import com.bilgehan.envanter.Model.Dto.InventoryHistoryDto;
import com.bilgehan.envanter.Model.request.GetHistoryRequest;
import com.bilgehan.envanter.Service.InventoryHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/inventoryHistory")
public class InventoryHistoryController {
    private final InventoryHistoryService inventoryHistoryService;

    public InventoryHistoryController(InventoryHistoryService inventoryHistoryService) {
        this.inventoryHistoryService = inventoryHistoryService;
    }

    @PostMapping("/")
    public ResponseEntity<List<InventoryHistoryDto>> getHistory(@RequestBody GetHistoryRequest getHistoryRequest) {
        return ResponseEntity.ok(inventoryHistoryService.getHistory(getHistoryRequest));
    }
}
