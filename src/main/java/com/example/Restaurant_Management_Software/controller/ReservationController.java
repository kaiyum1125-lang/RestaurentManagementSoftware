package com.example.Restaurant_Management_Software.controller;


import com.example.Restaurant_Management_Software.model.Reservation;
import com.example.Restaurant_Management_Software.repository.ReservationRepository;
import com.example.Restaurant_Management_Software.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;

    // ✅ Create
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation saved = reservationService.createReservation(reservation);
        return ResponseEntity.ok(saved);
    }

    // ✅ Read All
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    // ✅ Read By ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
    }

    // ✅ Update
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable Long id,
            @RequestBody Reservation updatedReservation
    ) {
        Reservation updated = reservationService.updateReservation(id, updatedReservation);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ✅ Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/approved")
    public List<Reservation> getApprovedReservations() {
        return reservationRepository.findByStatus("APPROVED");
    }

    @GetMapping("/rejected")
    public List<Reservation> getRejectedReservations() {
        return reservationRepository.findByStatus("REJECTED");
    }

    @PutMapping("/{id}/approve")
    public Reservation approveReservation(@PathVariable Long id) {
        Reservation r = reservationRepository.findById(id).orElseThrow();
        r.setStatus("APPROVED");
        return reservationRepository.save(r);
    }

    @PutMapping("/{id}/reject")
    public Reservation rejectReservation(@PathVariable Long id) {
        Reservation r = reservationRepository.findById(id).orElseThrow();
        r.setStatus("REJECTED");
        return reservationRepository.save(r);
    }
}
