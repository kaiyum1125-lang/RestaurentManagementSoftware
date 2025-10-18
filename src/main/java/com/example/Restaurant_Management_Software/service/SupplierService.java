package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.model.Supplier;
import com.example.Restaurant_Management_Software.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        return repository.findAll();
    }

    // Get supplier by ID
    public Supplier getSupplierById(Long id) {
        Optional<Supplier> optional = repository.findById(id);
        return optional.orElse(null);
    }

    // Create new supplier
    public Supplier createSupplier(Supplier supplier) {
        return repository.save(supplier);
    }

    // Update supplier
    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Supplier supplier = getSupplierById(id);
        if(supplier != null) {
            supplier.setName(supplierDetails.getName());
            supplier.setContact(supplierDetails.getContact());
            supplier.setEmail(supplierDetails.getEmail());
            supplier.setAddress(supplierDetails.getAddress());
            return repository.save(supplier);
        }
        return null;
    }

    // Delete supplier
    public boolean deleteSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        if(supplier != null) {
            repository.delete(supplier);
            return true;
        }
        return false;
    }

    // Optional: Find supplier by email
    public Supplier getSupplierByEmail(String email) {
        return repository.findByEmail(email);
    }
}