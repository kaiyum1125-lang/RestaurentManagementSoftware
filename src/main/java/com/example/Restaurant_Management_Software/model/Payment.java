package com.example.Restaurant_Management_Software.model;

import com.example.Restaurant_Management_Software.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method; // CASH, CARD, BKASH, NAGAD

    private LocalDateTime paymentDate = LocalDateTime.now();

}
