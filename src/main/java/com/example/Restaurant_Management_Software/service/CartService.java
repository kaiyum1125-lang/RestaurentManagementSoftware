package com.example.Restaurant_Management_Software.service;



import com.example.Restaurant_Management_Software.dto.AddToCartRequestDTO;
import com.example.Restaurant_Management_Software.dto.CartResponseDTO;
import com.example.Restaurant_Management_Software.dto.UpdateCartItemRequestDTO;
import com.example.Restaurant_Management_Software.model.Cart;
import com.example.Restaurant_Management_Software.model.CartItem;
import com.example.Restaurant_Management_Software.model.MenuItem;
import com.example.Restaurant_Management_Software.repository.CartItemRepository;
import com.example.Restaurant_Management_Software.repository.CartRepository;
import com.example.Restaurant_Management_Software.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MenuItemRepository menuItemRepository;
    private final CartMapper cartMapper;

    @Transactional
    public CartResponseDTO getOrCreateCart(String sessionId) {
        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseGet(() -> createNewCart(sessionId));
        return cartMapper.toDTO(cart);
    }

    @Transactional
    public CartResponseDTO addToCart(String sessionId, AddToCartRequestDTO request) {
        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseGet(() -> createNewCart(sessionId));

        MenuItem menuItem = menuItemRepository.findByIdAndAvailableTrue(request.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Menu item not found or unavailable: " + request.getMenuItemId()));

        // Check if item already exists in cart
        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndMenuItemId(cart.getId(), menuItem.getId());

        if (existingItem.isPresent()) {
            // Update quantity if item exists
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            cartItem.setSpecialInstructions(request.getSpecialInstructions());
            cartItem.calculateTotalPrice();
            cartItemRepository.save(cartItem);
        } else {
            // Add new item to cart
            CartItem cartItem = new CartItem();
            cartItem.setMenuItem(menuItem);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUnitPrice(menuItem.getPrice());
            cartItem.setSpecialInstructions(request.getSpecialInstructions());
            cartItem.calculateTotalPrice();
            cart.addCartItem(cartItem);
        }

        cart.calculateTotals();
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }

    @Transactional
    public CartResponseDTO updateCartItem(String sessionId, Long cartItemId, UpdateCartItemRequestDTO request) {
        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new RuntimeException("Cart not found for session: " + sessionId));

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found: " + cartItemId));

        if (request.getQuantity() != null) {
            cartItem.setQuantity(request.getQuantity());
        }
        if (request.getSpecialInstructions() != null) {
            cartItem.setSpecialInstructions(request.getSpecialInstructions());
        }

        cartItem.calculateTotalPrice();
        cart.calculateTotals();
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }

    @Transactional
    public CartResponseDTO removeFromCart(String sessionId, Long cartItemId) {
        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new RuntimeException("Cart not found for session: " + sessionId));

        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found: " + cartItemId));

        cart.removeCartItem(cartItem);
        cartItemRepository.delete(cartItem);

        cart.calculateTotals();
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);
    }

    @Transactional
    public void clearCart(String sessionId) {
        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new RuntimeException("Cart not found for session: " + sessionId));

        cart.getCartItems().clear();
        cart.calculateTotals();
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteCart(String sessionId) {
        cartRepository.deleteBySessionId(sessionId);
    }

    private Cart createNewCart(String sessionId) {
        Cart cart = new Cart();
        cart.setSessionId(sessionId);
        return cartRepository.save(cart);
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }
}