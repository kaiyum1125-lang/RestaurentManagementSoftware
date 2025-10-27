package com.example.Restaurant_Management_Software.dto;


import lombok.Data;
import java.util.List;

@Data
public class CartResponseDTO {
    private Long id;
    private String sessionId;
    private List<CartItemDTO> cartItems;
    private Double subtotal;
    private Double taxAmount;
    private Double totalAmount;
    private Integer totalItems;
}