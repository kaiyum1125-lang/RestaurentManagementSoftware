package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.model.Payment;
import com.example.Restaurant_Management_Software.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    // Get all payments
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    // Get payment by ID
    public Payment getPaymentById(Long id) {
        Optional<Payment> optional = repository.findById(id);
        return optional.orElse(null);
    }

    // Create new payment
    public Payment createPayment(Payment payment) {
        return repository.save(payment);
    }

    // Update payment
    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = getPaymentById(id);
        if (payment != null) {
            payment.setAmount(paymentDetails.getAmount());
            payment.setMethod(paymentDetails.getMethod());
            payment.setOrder(paymentDetails.getOrder());
            return repository.save(payment);
        }
        return null;
    }

    // Delete payment
    public boolean deletePayment(Long id) {
        Payment payment = getPaymentById(id);
        if (payment != null) {
            repository.delete(payment);
            return true;
        }
        return false;
    }

    // Optional: get payments by order
    public List<Payment> getPaymentsByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}