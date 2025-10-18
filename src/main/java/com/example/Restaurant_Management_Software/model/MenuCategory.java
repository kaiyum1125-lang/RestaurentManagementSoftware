package com.example.Restaurant_Management_Software.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<MenuItem> items;
}
