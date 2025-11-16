package com.example.Restaurant_Management_Software.model;

import com.example.Restaurant_Management_Software.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who placed the order
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // List of items in this order
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


    @Column(unique = true)
    private String orderNumber;
    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double taxAmount;

    @Column(nullable = false)
    private Double deliveryFee = 0.0;

    @Column(nullable = false)
    private Double discount = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType orderType;

    private String tableNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    private String customerName;

    private String customerPhone;

    private String deliveryAddress;

    @Column(columnDefinition = "TEXT")
    private String orderNotes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;



    private Double totalAmount;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = OrderStatus.PENDING;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    public enum OrderType {
        DINE_IN, TAKEAWAY, DELIVERY
    }
    public enum OrderStatus {
        PENDING,CONFIRMED, PREPARING, IN_PROGRESS, COMPLETED, CANCELLED
    }

}
