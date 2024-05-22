package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.CartItem;
import com.example.ecommercebe.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    List<CartItem> findCartItemsByShoppingCart_Id(Integer id);
}
