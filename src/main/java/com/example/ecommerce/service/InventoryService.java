package com.example.ecommerce.service;

import com.example.ecommerce.entity.Inventory;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.InventoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Inventory createInventory(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setAvailableQuantity(quantity);
        inventory.setReservedQuantity(0);

        return inventoryRepository.save(inventory);
    }

    public Inventory getInventory(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product"));
    }

    public Inventory updateQuantity(Long productId, Integer quantity) {
        Inventory inventory = getInventory(productId);
        inventory.setAvailableQuantity(quantity);
        return inventoryRepository.save(inventory);
    }

    public boolean isAvailable(Long productId, Integer quantity) {
        Inventory inventory = getInventory(productId);
        return inventory.getAvailableQuantity() >= quantity;
    }

    public void reserveQuantity(Long productId, Integer quantity) {
        Inventory inventory = getInventory(productId);
        inventory.setReservedQuantity(inventory.getReservedQuantity() + quantity);
        inventoryRepository.save(inventory);
    }

    public void releaseQuantity(Long productId, Integer quantity) {
        Inventory inventory = getInventory(productId);
        inventory.setReservedQuantity(Math.max(0, inventory.getReservedQuantity() - quantity));
        inventoryRepository.save(inventory);
    }
}
