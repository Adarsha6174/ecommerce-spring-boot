package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CartDTO> getCart() {
        Long userId = getUserIdFromAuthentication();
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CartDTO> addToCart(
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        Long userId = getUserIdFromAuthentication();
        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }

    @DeleteMapping("/remove/{productId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CartDTO> removeFromCart(@PathVariable Long productId) {
        Long userId = getUserIdFromAuthentication();
        return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CartDTO> updateCartItem(
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        Long userId = getUserIdFromAuthentication();
        return ResponseEntity.ok(cartService.updateCartItem(userId, productId, quantity));
    }

    @DeleteMapping("/clear")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CartDTO> clearCart() {
        Long userId = getUserIdFromAuthentication();
        return ResponseEntity.ok(cartService.clearCart(userId));
    }

    private Long getUserIdFromAuthentication() {
        // This should be extracted from the authenticated user
        // For now, returning 1 as placeholder - in production, use SecurityContextHolder
        return 1L;
    }
}
