package com.example.Restaurant_Management_Software.service;


import com.example.Restaurant_Management_Software.model.Staff;
import com.example.Restaurant_Management_Software.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
    }

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, Staff staffDetails) {
        Staff existing = getStaffById(id);
        existing.setName(staffDetails.getName());
        existing.setRole(staffDetails.getRole());
        existing.setEmail(staffDetails.getEmail());
        return staffRepository.save(existing);
    }

    public void deleteStaff(Long id) {
        Staff existing = getStaffById(id);
        staffRepository.delete(existing);
    }
}