package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.FavoriteProducts;
import com.example.ecommercebe.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface FavoriteProductService {
    Page<Product> getAllFavoriteProducts(Long userId, Pageable pageable);
    void addFavoriteProduct(Long userId, Long productId);
    void deleteFavoriteProduct(Long userId, Long productId);
}
