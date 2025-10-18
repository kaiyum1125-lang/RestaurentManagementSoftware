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

    @Data
    public static class OrderItemResponse {
        private String menuItemName;
        private Integer quantity;
        private BigDecimal unitPrice;
        private String specialInstructions;
    }
}
