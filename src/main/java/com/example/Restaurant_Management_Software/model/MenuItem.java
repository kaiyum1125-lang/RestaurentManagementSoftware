package com.example.Restaurant_Management_Software.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private boolean available = true;
    private String imageUrl;

    // ManyToOne relationship with MenuCategory
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("items")
    private MenuCategory category;
}
