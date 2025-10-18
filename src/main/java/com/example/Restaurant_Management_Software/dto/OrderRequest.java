package com.example.Restaurant_Management_Software.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId; // Who placed the order
    private List<OrderItemRequest> items;

    @Data
    public static class OrderItemRequest {
        private Long menuItemId;
        private Integer quantity;
        private String specialInstructions;
    }
}
