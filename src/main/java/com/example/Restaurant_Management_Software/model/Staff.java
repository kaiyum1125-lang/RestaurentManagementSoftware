package com.example.Restaurant_Management_Software.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "staff")
@Data
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private String email;
}