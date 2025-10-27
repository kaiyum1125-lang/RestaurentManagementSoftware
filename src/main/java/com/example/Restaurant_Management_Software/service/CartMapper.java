package com.example.Restaurant_Management_Software.service;



import com.example.Restaurant_Management_Software.dto.*;
import com.example.Restaurant_Management_Software.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapper {

    public CartResponseDTO toDTO(Cart cart) {
        CartResponseDTO dto = new CartResponseDTO();
        dto.setId(cart.getId());
        dto.setSessionId(cart.getSessionId());
        dto.setSubtotal(cart.getSubtotal());
        dto.setTaxAmount(cart.getTaxAmount());
        dto.setTotalAmount(cart.getTotalAmount());
        dto.setTotalItems(calculateTotalItems(cart));

        // Map cart items
        dto.setCartItems(cart.getCartItems().stream()
                .map(this::toCartItemDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    private CartItemDTO toCartItemDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setMenuItemId(cartItem.getMenuItem().getId());
        dto.setName(cartItem.getMenuItem().getName());
        dto.setPrice(cartItem.getUnitPrice());
        dto.setQuantity(cartItem.getQuantity());
        dto.setImageUrl(cartItem.getMenuItem().getImageUrl());
        dto.setSpecialInstructions(cartItem.getSpecialInstructions());

        if (cartItem.getMenuItem().getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(cartItem.getMenuItem().getCategory().getId());
            categoryDTO.setName(cartItem.getMenuItem().getCategory().getName());
            dto.setCategory(categoryDTO);
        }

        return dto;
    }

    private Integer calculateTotalItems(Cart cart) {
        return cart.getCartItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }
}