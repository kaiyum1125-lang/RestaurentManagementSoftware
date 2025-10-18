package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.model.Feedback;
import com.example.Restaurant_Management_Software.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    public List<Feedback> getAllFeedbacks() {
        return repository.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Feedback createFeedback(Feedback feedback) {
        return repository.save(feedback);
    }

    public Feedback updateFeedback(Long id, Feedback feedbackDetails) {
        Feedback feedback = getFeedbackById(id);
        if(feedback != null) {
            feedback.setRating(feedbackDetails.getRating());
            feedback.setComment(feedbackDetails.getComment());
            feedback.setStatus(feedbackDetails.getStatus());
            return repository.save(feedback);
        }
        return null;
    }

    public boolean deleteFeedback(Long id) {
        Feedback feedback = getFeedbackById(id);
        if(feedback != null) {
            repository.delete(feedback);
            return true;
        }
        return false;
    }

    public List<Feedback> getFeedbacksByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public List<Feedback> getFeedbacksByOrder(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}