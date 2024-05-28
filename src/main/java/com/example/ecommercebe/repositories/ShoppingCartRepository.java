package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findShoppingCartByUser_Id(Long id);
}
