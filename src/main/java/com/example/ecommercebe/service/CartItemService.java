package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.CartItem;
import com.example.ecommercebe.repositories.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> findCartItemsByShoppingCartId(Integer id) {
        return cartItemRepository.findCartItemsByShoppingCart_Id(id);
    }
}
