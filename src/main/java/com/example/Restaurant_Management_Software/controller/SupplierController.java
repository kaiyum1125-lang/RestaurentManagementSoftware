package com.example.Restaurant_Management_Software.controller;

import com.example.Restaurant_Management_Software.model.Supplier;
import com.example.Restaurant_Management_Software.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService service;

    // GET all suppliers
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return service.getAllSuppliers();
    }

    // GET supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = service.getSupplierById(id);
        if(supplier != null) {
            return ResponseEntity.ok(supplier);
        }
        return ResponseEntity.notFound().build();
    }

    // POST create supplier
    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return service.createSupplier(supplier);
    }

    // PUT update supplier
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        Supplier updated = service.updateSupplier(id, supplier);
        if(updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        boolean deleted = service.deleteSupplier(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Optional: GET supplier by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Supplier> getSupplierByEmail(@PathVariable String email) {
        Supplier supplier = service.getSupplierByEmail(email);
        if(supplier != null) {
            return ResponseEntity.ok(supplier);
        }
        return ResponseEntity.notFound().build();
    }
}
