package com.example.Restaurant_Management_Software.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String contactNumber;

    private LocalDate date;
    private String timeSlot;
    private int numberOfGuests;

    private String status = "PENDING";
}