package com.example.Restaurant_Management_Software.repository;

import com.example.Restaurant_Management_Software.model.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
