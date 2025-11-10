package com.example.Restaurant_Management_Software.service;


import com.example.Restaurant_Management_Software.model.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation updateReservation(Long id, Reservation updatedReservation);
    void deleteReservation(Long id);
}
