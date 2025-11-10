package com.example.Restaurant_Management_Software.controller;


import com.example.Restaurant_Management_Software.model.Staff;
import com.example.Restaurant_Management_Software.service.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // Allow Angular frontend
@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    // Constructor Injection (best practice)
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    //  Get all staff
    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        return ResponseEntity.ok(staffList);
    }

    //  Get staff by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Staff>> getStaffById(@PathVariable Long id) {
        Optional<Staff> staff = Optional.ofNullable(staffService.getStaffById(id));
        return ResponseEntity.ok(staff);
    }

    //  Create new staff
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff savedStaff = staffService.saveStaff(staff);
        return ResponseEntity.ok(savedStaff);
    }

    //  Update existing staff
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        Staff updatedStaff = staffService.updateStaff(id, staff);
        return ResponseEntity.ok(updatedStaff);
    }

    // Delete staff
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Staff with ID " + id + " deleted successfully.");
    }
}