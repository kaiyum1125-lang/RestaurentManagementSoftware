package com.example.Restaurant_Management_Software.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Purchase purchase;

    @ManyToOne
    private InventoryItem inventoryItem;

    private int quantity;
    private double price;
}