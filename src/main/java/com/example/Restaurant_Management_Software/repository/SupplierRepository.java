package com.example.Restaurant_Management_Software.repository;

import com.example.Restaurant_Management_Software.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Optional: custom query methods if needed
    Supplier findByEmail(String email);
}