package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.model.MenuCategory;
import com.example.Restaurant_Management_Software.repository.MenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuCategoryService {

    @Autowired
    private MenuCategoryRepository repository;

    // Get all categories
    public List<MenuCategory> getAllCategories() {
        return repository.findAll();
    }

    // Get category by ID
    public MenuCategory getCategoryById(Long id) {
        Optional<MenuCategory> optional = repository.findById(id);
        return optional.orElse(null); // null যদি না পাওয়া যায়
    }

    // Save new category
    public MenuCategory createCategory(MenuCategory category) {
        return repository.save(category);
    }

    // Update category
    public MenuCategory updateCategory(Long id, MenuCategory categoryDetails) {
        MenuCategory category = getCategoryById(id);
        if(category != null) {
            category.setName(categoryDetails.getName());
            category.setDescription(categoryDetails.getDescription());
            return repository.save(category);
        }
        return null;
    }

    // Delete category
    public boolean deleteCategory(Long id) {
        MenuCategory category = getCategoryById(id);
        if(category != null) {
            repository.delete(category);
            return true;
        }
        return false;
    }
}