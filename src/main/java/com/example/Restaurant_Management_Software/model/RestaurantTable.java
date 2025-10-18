package com.example.Restaurant_Management_Software.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "restaurant_tables")
@Data
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tableNumber;

    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private TableStatus status = TableStatus.AVAILABLE;

    public enum TableStatus {
        AVAILABLE, OCCUPIED, RESERVED, CLEANING
    }
}