package com.example.Restaurant_Management_Software.repository;


import com.example.Restaurant_Management_Software.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStatus(String status);
}