package com.example.Restaurant_Management_Software.service;


import com.example.Restaurant_Management_Software.model.PurchaseItem;
import com.example.Restaurant_Management_Software.repository.PurchaseItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseItemService {

    @Autowired
    private PurchaseItemRepository repository;

    // Get all purchase items
    public List<PurchaseItem> getAllPurchaseItems() {
        return repository.findAll();
    }

    // Get purchase item by ID
    public PurchaseItem getPurchaseItemById(Long id) {
        Optional<PurchaseItem> optional = repository.findById(id);
        return optional.orElse(null);
    }

    // Create purchase item
    public PurchaseItem createPurchaseItem(PurchaseItem item) {
        return repository.save(item);
    }

    // Update purchase item
    public PurchaseItem updatePurchaseItem(Long id, PurchaseItem itemDetails) {
        PurchaseItem item = getPurchaseItemById(id);
        if (item != null) {
            item.setPurchase(itemDetails.getPurchase());
            item.setInventoryItem(itemDetails.getInventoryItem());
            item.setQuantity(itemDetails.getQuantity());
            item.setPrice(itemDetails.getPrice());
            return repository.save(item);
        }
        return null;
    }

    // Delete purchase item
    public boolean deletePurchaseItem(Long id) {
        PurchaseItem item = getPurchaseItemById(id);
        if (item != null) {
            repository.delete(item);
            return true;
        }
        return false;
    }

    // Optional: Get all items for a specific purchase
    public List<PurchaseItem> getItemsByPurchaseId(Long purchaseId) {
        return repository.findByPurchaseId(purchaseId);
    }
}