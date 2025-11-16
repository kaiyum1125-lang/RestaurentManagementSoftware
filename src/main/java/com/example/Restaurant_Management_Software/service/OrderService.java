package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.dto.OrderRequest;
import com.example.Restaurant_Management_Software.dto.OrderResponse;
import com.example.Restaurant_Management_Software.model.MenuItem;
import com.example.Restaurant_Management_Software.model.Order;
import com.example.Restaurant_Management_Software.model.OrderItem;
import com.example.Restaurant_Management_Software.model.User;
import com.example.Restaurant_Management_Software.repository.MenuItemRepository;
import com.example.Restaurant_Management_Software.repository.OrderRepository;
import com.example.Restaurant_Management_Software.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final UserRepository userRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.OrderStatus.PENDING);

        // 🟢 FIX: No `toString()` here — directly set enum
        order.setOrderType(request.getOrderType());

        order.setTableNumber(request.getTableNumber());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setCustomerName(request.getCustomerName());
        order.setCustomerPhone(request.getCustomerPhone());
        order.setDeliveryAddress(request.getDeliveryAddress());
        order.setOrderNotes(request.getOrderNotes());

        order.setOrderItems(new ArrayList<>());
        double subtotal = 0d;

        for (OrderRequest.OrderItemRequest itemRequest : request.getItems()) {

            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(menuItem.getPrice());
            orderItem.setSpecialInstructions(itemRequest.getSpecialInstructions());

            subtotal += menuItem.getPrice() * itemRequest.getQuantity();
            order.getOrderItems().add(orderItem);
        }

        order.setSubtotal(subtotal);
        order.setTaxAmount(subtotal * 0.1);

        // 🟢 FIX: compare enum correctly
        order.setDeliveryFee(request.getOrderType() == Order.OrderType.DELIVERY ? 3.99 : 0.0);

        order.setDiscount(0.0);
        order.setTotalAmount(order.getSubtotal() + order.getTaxAmount() + order.getDeliveryFee());

        Order savedOrder = orderRepository.save(order);
        savedOrder.setOrderNumber("ORD-" + savedOrder.getId());
        orderRepository.save(savedOrder);

        return mapToOrderResponse(savedOrder);
    }

    public OrderResponse updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        return mapToOrderResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status)
                .stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(this::mapToOrderResponse)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id))
            throw new RuntimeException("Order not found");
        orderRepository.deleteById(id);
    }

    private OrderResponse mapToOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setStatus(order.getStatus().name());
        response.setSubtotal(order.getSubtotal());
        response.setTaxAmount(order.getTaxAmount());
        response.setDeliveryFee(order.getDeliveryFee());
        response.setDiscount(order.getDiscount());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());
        response.setUserName(order.getUser().getUsername());
        response.setOrderNumber(order.getOrderNumber());
        response.setOrderType(order.getOrderType().name());

        response.setItems(order.getOrderItems().stream().map(item -> {
            OrderResponse.OrderItemResponse res = new OrderResponse.OrderItemResponse();
            res.setMenuItemName(item.getMenuItem().getName());
            res.setQuantity(item.getQuantity());
            res.setUnitPrice(item.getUnitPrice());
            res.setSpecialInstructions(item.getSpecialInstructions());
            return res;
        }).collect(Collectors.toList()));

        return response;
    }

    public List<OrderResponse> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }
}
