package com.example.Restaurant_Management_Software.repository;

import com.example.Restaurant_Management_Software.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Optional: find all payments for a specific order
    List<Payment> findByOrderId(Long orderId);
}