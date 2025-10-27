package com.example.Restaurant_Management_Software.dto;

import lombok.Data;

@Data
public class AddToCartRequestDTO {
    private Long menuItemId;
    private Integer quantity = 1;
    private String specialInstructions;
}