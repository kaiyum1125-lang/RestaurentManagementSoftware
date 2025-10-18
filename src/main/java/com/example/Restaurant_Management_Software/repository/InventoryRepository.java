package com.example.Restaurant_Management_Software.repository;

import com.example.Restaurant_Management_Software.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

    // Find items with quantity less than a given number
    List<InventoryItem> findByQuantityLessThan(Integer quantity);

    // Find items that are low in stock (below or equal to minStockLevel)
    @Query("SELECT i FROM InventoryItem i WHERE i.quantity <= i.minStockLevel")
    List<InventoryItem> findLowStockItems();
}
