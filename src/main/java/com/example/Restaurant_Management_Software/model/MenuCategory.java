package com.example.Restaurant_Management_Software.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_category")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    private String icon; // New field for category icon

    // Audit fields
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("category")
    private List<MenuItem> items = new ArrayList<>();

    // Constructors
    public MenuCategory() {
    }

    public MenuCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public MenuCategory(String name, String description, String icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    // Helper method to add item
    public void addMenuItem(MenuItem item) {
        items.add(item);
        item.setCategory(this);
    }

    // Helper method to remove item
    public void removeMenuItem(MenuItem item) {
        items.remove(item);
        item.setCategory(null);
    }

    // Get item count
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
