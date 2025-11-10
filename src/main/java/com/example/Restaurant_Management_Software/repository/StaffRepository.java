package com.example.Restaurant_Management_Software.repository;



import com.example.Restaurant_Management_Software.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}