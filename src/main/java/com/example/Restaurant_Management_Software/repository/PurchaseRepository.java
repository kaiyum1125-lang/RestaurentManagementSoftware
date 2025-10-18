package com.example.Restaurant_Management_Software.repository;

import com.example.Restaurant_Management_Software.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    // Optional: find all purchases by supplier
}
