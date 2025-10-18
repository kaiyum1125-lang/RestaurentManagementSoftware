package com.example.Restaurant_Management_Software.repository;

import com.example.Restaurant_Management_Software.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

    // Find all items for a specific purchase
    List<PurchaseItem> findByPurchaseId(Long purchaseId);
}