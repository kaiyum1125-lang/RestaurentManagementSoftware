package com.example.Restaurant_Management_Software.controller;

import com.example.Restaurant_Management_Software.model.MenuCategory;
import com.example.Restaurant_Management_Software.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-categories")
public class MenuCategoryController {

    @Autowired
    private MenuCategoryService service;

    // GET all categories
    @GetMapping
    public List<MenuCategory> getAllCategories() {
        return service.getAllCategories();
    }

    // GET category by id
    @GetMapping("/{id}")
    public ResponseEntity<MenuCategory> getCategoryById(@PathVariable Long id) {
        MenuCategory category = service.getCategoryById(id);
        if(category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    // POST create new category
    @PostMapping
    public MenuCategory createCategory(@RequestBody MenuCategory category) {
        return service.createCategory(category);
    }

    // PUT update category
    @PutMapping("/{id}")
    public ResponseEntity<MenuCategory> updateCategory(@PathVariable Long id, @RequestBody MenuCategory category) {
        MenuCategory updated = service.updateCategory(id, category);
        if(updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        boolean deleted = service.deleteCategory(id);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}