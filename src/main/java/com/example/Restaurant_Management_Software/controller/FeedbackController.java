package com.example.Restaurant_Management_Software.controller;

import com.example.Restaurant_Management_Software.model.Feedback;
import com.example.Restaurant_Management_Software.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return service.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = service.getFeedbackById(id);
        if(feedback != null) return ResponseEntity.ok(feedback);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return service.createFeedback(feedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        Feedback updated = service.updateFeedback(id, feedback);
        if(updated != null) return ResponseEntity.ok(updated);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        boolean deleted = service.deleteFeedback(id);
        if(deleted) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{customerId}")
    public List<Feedback> getFeedbacksByCustomer(@PathVariable Long customerId) {
        return service.getFeedbacksByCustomer(customerId);
    }

    @GetMapping("/order/{orderId}")
    public List<Feedback> getFeedbacksByOrder(@PathVariable Long orderId) {
        return service.getFeedbacksByOrder(orderId);
    }
}