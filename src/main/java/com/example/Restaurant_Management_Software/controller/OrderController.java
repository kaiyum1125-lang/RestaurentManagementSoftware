package com.example.Restaurant_Management_Software.controller;

import com.example.Restaurant_Management_Software.dto.OrderRequest;
import com.example.Restaurant_Management_Software.dto.OrderResponse;
import com.example.Restaurant_Management_Software.model.Order;
import com.example.Restaurant_Management_Software.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

@Tag(name = "Order Management", description = "APIs for managing restaurant orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * ✅ Create a new order
     */
    @PostMapping
    @Operation(summary = "Create a new order")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    /**
     * ✅ Get all orders by status
     */
    @GetMapping("/status/{status}")
    @Operation(summary = "Get orders by status")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(@PathVariable("status") Order.OrderStatus status) {
        List<OrderResponse> responses = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(responses);
    }

    /**
     * ✅ Get a single order by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        OrderResponse response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * ✅ Update order status (PENDING → IN_PROGRESS → COMPLETED → CANCELLED)
     */
    @PutMapping("/{id}/status")
    @Operation(summary = "Update order status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam("status") Order.OrderStatus status) {
        OrderResponse response = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete order by ID
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete order by ID")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Order deleted successfully (ID: " + id + ")");
    }
}
