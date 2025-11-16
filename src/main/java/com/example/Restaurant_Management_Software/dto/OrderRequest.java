package com.example.Restaurant_Management_Software.dto;

import com.example.Restaurant_Management_Software.enums.PaymentMethod;
import com.example.Restaurant_Management_Software.model.Order;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequest {
    private Long userId; // Who placed the order
    private List<OrderItemRequest> items;
//    private Order.OrderType getOrderType;
    private String tableNumber;
    private PaymentMethod paymentMethod;
    private String customerName;
    private String customerPhone;
    private String deliveryAddress;
    private String orderNotes;
    private Order.OrderType orderType;
//    private Long userId;



    @Data
    public static class OrderItemRequest {
        private Long menuItemId;
        private Integer quantity;
        private String specialInstructions;
    }
}
