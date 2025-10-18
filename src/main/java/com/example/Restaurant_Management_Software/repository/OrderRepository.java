package com.example.Restaurant_Management_Software.repository;

import com.example.Restaurant_Management_Software.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(Order.OrderStatus status);

    List<Order> findByCreatedAtBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);

}
