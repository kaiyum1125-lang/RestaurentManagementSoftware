package com.example.Restaurant_Management_Software.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Customer who gave the feedback
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Optional: linked order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    private Order order;

    // Rating (1 to 5)
    private int rating;

    // Feedback comment
    @Column(length = 1000)
    private String comment;

    // Feedback submission date
    private LocalDateTime feedbackDate = LocalDateTime.now();

    // Optional: feedback status
    @Enumerated(EnumType.STRING)
    private FeedbackStatus status = FeedbackStatus.PENDING;

    public enum FeedbackStatus {
        PENDING,
        REVIEWED,
        RESOLVED
    }
}
