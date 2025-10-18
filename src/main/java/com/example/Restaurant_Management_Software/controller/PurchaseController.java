package com.example.Restaurant_Management_Software.controller;

import com.example.Restaurant_Management_Software.model.Purchase;
import com.example.Restaurant_Management_Software.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    // GET all purchases
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return service.getAllPurchases();
    }

    // GET purchase by ID
    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id) {
        Purchase purchase = service.getPurchaseById(id);
        if (purchase != null) {
            return ResponseEntity.ok(purchase);
        }
        return ResponseEntity.notFound().build();
    }

    // POST create purchase
    @PostMapping
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return service.createPurchase(purchase);
    }

    // PUT update purchase
    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id, @RequestBody Purchase purchase) {
        Purchase updated = service.updatePurchase(id, purchase);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE purchase
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        boolean deleted = service.deletePurchase(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}