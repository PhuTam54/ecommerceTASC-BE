package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findShoppingCartByUser_Id(Long user);
}
