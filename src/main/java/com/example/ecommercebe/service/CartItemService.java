package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.CartItemDTO;
import com.example.ecommercebe.mapper.CartItemMapper;
import com.example.ecommercebe.repositories.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
//    public CartItemService(CartItemRepository cartItemRepository, CartItemMapper cartItemMapper) {
//        this.cartItemRepository = cartItemRepository;
//        this.cartItemMapper = cartItemMapper;
//    }

    public List<CartItemDTO> findCartItemsByShoppingCartId(Integer id) {
        return cartItemRepository.findCartItemsByShoppingCart_Id(id).stream().map(cartItemMapper::cartItemToCartITemDTO).collect(Collectors.toList());
    }
}
