package com.example.Restaurant_Management_Software.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private boolean available = true;

    private String imageUrl;

    // New fields for frontend enhancements
    @Column(name = "is_popular")
    private Boolean isPopular = false;

    @Column(name = "is_spicy")
    private Boolean isSpicy = false;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    // ManyToOne relationship with MenuCategory
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("items")
    private MenuCategory category;

    // Audit fields
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public MenuItem() {
    }

    public MenuItem(String name, String description, Double price, boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    // Helper methods
    public Boolean getIsPopular() {
        return isPopular != null ? isPopular : false;
    }

    public Boolean getIsSpicy() {
        return isSpicy != null ? isSpicy : false;
    }
}
