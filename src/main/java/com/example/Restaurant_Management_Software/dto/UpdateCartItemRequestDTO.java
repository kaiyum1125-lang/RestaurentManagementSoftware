package com.example.Restaurant_Management_Software.dto;


import lombok.Data;

@Data
public class UpdateCartItemRequestDTO {
    private Integer quantity;
    private String specialInstructions;
}