package com.example.ecommercebe.repository;

import com.example.ecommercebe.entity.CartItem;
import com.example.ecommercebe.entity.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    List<CartItem> findCartItemsByShoppingCart_Id(Integer id);
}
