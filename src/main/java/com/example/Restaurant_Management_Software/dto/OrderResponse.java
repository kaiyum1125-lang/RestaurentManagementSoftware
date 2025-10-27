package com.example.Restaurant_Management_Software.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private String userName;
    private String status;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItemResponse> items;
    private String orderNumber;
    private Double subtotal;
    private Double taxAmount;
    private Double deliveryFee;
    private Double discount;
    private String orderType;
    private String tableNumber;
    private String paymentMethod;
    private String customerName;
    private String customerPhone;
    private String deliveryAddress;
    private String orderNotes;
    private String estimatedTime;

    @Data
    public static class OrderItemResponse {
        private String menuItemName;
        private Integer quantity;
        private Double unitPrice;
        private String specialInstructions;
    }
}
