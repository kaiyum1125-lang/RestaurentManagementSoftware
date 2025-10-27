package com.example.Restaurant_Management_Software.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private String imageUrl;
    private CategoryDTO category;
    private String specialInstructions;
    private Long menuItemId;
}

