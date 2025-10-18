package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.model.Purchase;
import com.example.Restaurant_Management_Software.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;

    // Get all purchases
    public List<Purchase> getAllPurchases() {
        return repository.findAll();
    }

    // Get purchase by ID
    public Purchase getPurchaseById(Long id) {
        Optional<Purchase> optional = repository.findById(id);
        return optional.orElse(null);
    }

    // Create new purchase
    public Purchase createPurchase(Purchase purchase) {
        return repository.save(purchase);
    }

    // Update purchase
    public Purchase updatePurchase(Long id, Purchase purchaseDetails) {
        Purchase purchase = getPurchaseById(id);
        if (purchase != null) {
            purchase.setSupplier(purchaseDetails.getSupplier());
            purchase.setPurchaseDate(purchaseDetails.getPurchaseDate());
            purchase.setTotalAmount(purchaseDetails.getTotalAmount());
            purchase.setItems(purchaseDetails.getItems());
            return repository.save(purchase);
        }
        return null;
    }

    // Delete purchase
    public boolean deletePurchase(Long id) {
        Purchase purchase = getPurchaseById(id);
        if (purchase != null) {
            repository.delete(purchase);
            return true;
        }
        return false;
    }
}
