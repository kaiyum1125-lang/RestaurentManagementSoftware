package com.example.Restaurant_Management_Software.repository;


import com.example.Restaurant_Management_Software.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByCustomerId(Long customerId);
    List<Feedback> findByOrderId(Long orderId);
}