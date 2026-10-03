package com.example.ecommerce.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Inventory;

public interface InventoryRepository
        extends JpaRepository<Inventory, Long> {
	Optional<Inventory> findByProductId(Long productId);
}