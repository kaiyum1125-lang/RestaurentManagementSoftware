package com.example.Restaurant_Management_Software.controller;

import com.example.Restaurant_Management_Software.model.User;
import com.example.Restaurant_Management_Software.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Manage restaurant users (Admin, Waiter, Manager, etc.)")
public class UserController {

    private final UserService userService;

    /**
     * ✅ Create a new user
     */
    @Operation(summary = "Create new user", description = "Register a new restaurant staff or admin.")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * ✅ Get all users
     */
    @Operation(summary = "Get all users", description = "List all registered users.")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * ✅ Get user by ID
     */
    @Operation(summary = "Get user by ID", description = "Fetch details of a single user.")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * ✅ Update user by ID
     */
    @Operation(summary = "Update user", description = "Update user details like role, phone, email, etc.")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    /**
     * ✅ Delete user
     */
    @Operation(summary = "Delete user", description = "Remove a user from the system.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
