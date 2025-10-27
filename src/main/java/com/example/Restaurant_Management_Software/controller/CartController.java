package com.example.Restaurant_Management_Software.controller;

import com.example.Restaurant_Management_Software.dto.AddToCartRequestDTO;
import com.example.Restaurant_Management_Software.dto.CartResponseDTO;
import com.example.Restaurant_Management_Software.dto.UpdateCartItemRequestDTO;
import com.example.Restaurant_Management_Software.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart(@RequestParam String sessionId) {
        return ResponseEntity.ok(cartService.getOrCreateCart(sessionId));
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponseDTO> addToCart(
            @RequestParam String sessionId,
            @RequestBody AddToCartRequestDTO request) {
        return ResponseEntity.ok(cartService.addToCart(sessionId, request));
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> updateCartItem(
            @RequestParam String sessionId,
            @PathVariable Long cartItemId,
            @RequestBody UpdateCartItemRequestDTO request) {
        return ResponseEntity.ok(cartService.updateCartItem(sessionId, cartItemId, request));
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> removeFromCart(
            @RequestParam String sessionId,
            @PathVariable Long cartItemId) {
        return ResponseEntity.ok(cartService.removeFromCart(sessionId, cartItemId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam String sessionId) {
        cartService.clearCart(sessionId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCart(@RequestParam String sessionId) {
        cartService.deleteCart(sessionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/session")
    public ResponseEntity<String> createSession() {
        return ResponseEntity.ok(cartService.generateSessionId());
    }
}