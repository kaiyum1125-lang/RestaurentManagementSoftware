package com.example.Restaurant_Management_Software.service;


import com.example.Restaurant_Management_Software.model.Reservation;
import com.example.Restaurant_Management_Software.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        return reservationRepository.findById(id).map(existing -> {
            existing.setCustomerName(updatedReservation.getCustomerName());
            existing.setContactNumber(updatedReservation.getContactNumber());
            existing.setDate(updatedReservation.getDate());
            existing.setTimeSlot(updatedReservation.getTimeSlot());
            existing.setNumberOfGuests(updatedReservation.getNumberOfGuests());
            existing.setStatus(updatedReservation.getStatus());
            return reservationRepository.save(existing);
        }).orElse(null);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
