package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.model.Customer;
import com.example.Restaurant_Management_Software.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    // Get all customers
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Long id) {
        Optional<Customer> optional = repository.findById(id);
        return optional.orElse(null);
    }

    // Create new customer
    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    // Update customer
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = getCustomerById(id);
        if(customer != null) {
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPhone(customerDetails.getPhone());
            customer.setAddress(customerDetails.getAddress());
            customer.setActive(customerDetails.isActive());
            return repository.save(customer);
        }
        return null;
    }

    // Delete customer
    public boolean deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        if(customer != null) {
            repository.delete(customer);
            return true;
        }
        return false;
    }

    // Optional: find by email
    public Customer getCustomerByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }
}
