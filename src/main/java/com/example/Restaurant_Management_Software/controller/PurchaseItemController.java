package com.example.Restaurant_Management_Software.controller;

import com.example.Restaurant_Management_Software.model.PurchaseItem;
import com.example.Restaurant_Management_Software.service.PurchaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-items")
public class PurchaseItemController {

    @Autowired
    private PurchaseItemService service;

    // GET all purchase items
    @GetMapping
    public List<PurchaseItem> getAllPurchaseItems() {
        return service.getAllPurchaseItems();
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseItem> getPurchaseItemById(@PathVariable Long id) {
        PurchaseItem item = service.getPurchaseItemById(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        }
        return ResponseEntity.notFound().build();
    }

    // POST create item
    @PostMapping
    public PurchaseItem createPurchaseItem(@RequestBody PurchaseItem item) {
        return service.createPurchaseItem(item);
    }

    // PUT update item
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseItem> updatePurchaseItem(@PathVariable Long id, @RequestBody PurchaseItem item) {
        PurchaseItem updated = service.updatePurchaseItem(id, item);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseItem(@PathVariable Long id) {
        boolean deleted = service.deletePurchaseItem(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // GET all items for a specific purchase
    @GetMapping("/purchase/{purchaseId}")
    public List<PurchaseItem> getItemsByPurchase(@PathVariable Long purchaseId) {
        return service.getItemsByPurchaseId(purchaseId);
    }
}
