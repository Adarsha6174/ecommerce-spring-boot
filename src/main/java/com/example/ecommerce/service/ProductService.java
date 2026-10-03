package com.example.ecommerce.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.dto.ProductDTO;

public interface ProductService {

    Product create(Product product);

    Product getById(Long id);

    List<Product> getAll();

    Product update(Long id, Product product);

    void delete(Long id);
    
    Page<Product> getByCategory(Long categoryId, Pageable pageable);
    
    Page<Product> search(String keyword, Pageable pageable);
    
    ProductDTO getProductWithInventory(Long id);
    
    List<ProductDTO> getAllWithInventory();
}