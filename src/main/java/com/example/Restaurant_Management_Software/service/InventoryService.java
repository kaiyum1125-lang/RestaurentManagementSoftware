package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.model.InventoryItem;
import com.example.Restaurant_Management_Software.repository.InventoryRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryItem> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    public List<InventoryItem> getLowStockItems() {
        return inventoryRepository.findLowStockItems();
    }

    public InventoryItem createInventoryItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    public InventoryItem updateInventoryQuantity(Long itemId, Integer newQuantity) {
        InventoryItem item = inventoryRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        item.setQuantity(newQuantity);
        item.setLastRestocked(LocalDateTime.now());

        return inventoryRepository.save(item);
    }

    public void deleteInventoryItem(Long id) {
        inventoryRepository.deleteById(id);
    }
}