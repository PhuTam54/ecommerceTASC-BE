package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.CartItemDTO;
import com.example.ecommercebe.entities.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItemDTO cartItemToCartITemDTO(CartItem cartItem){
        CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setProductId(cartItem.getProduct().getId());
            cartItemDTO.setClinicId(cartItem.getClinic().getId());
            cartItemDTO.setShoppingCartId(cartItem.getShoppingCart().getId());
            cartItemDTO.setQuantity(cartItem.getQuantity());
            cartItemDTO.setTotal(cartItem.getTotal());


        return cartItemDTO;
    }

}
