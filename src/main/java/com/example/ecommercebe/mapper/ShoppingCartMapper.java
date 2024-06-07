package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.CartItemDTO;
import com.example.ecommercebe.dto.ShoppingCartDTO;
import com.example.ecommercebe.entities.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShoppingCartMapper {

    private final CartItemMapper cartItemMapper;

    public ShoppingCartDTO shoppingCartToShoppingCartDTO(ShoppingCart shoppingCart){
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        Set<CartItemDTO> cartItemDTOS = shoppingCart.getCartItems().stream().map(cartItemMapper::cartItemToCartITemDTO).collect(Collectors.toSet());
            shoppingCartDTO.setId(shoppingCart.getId());
            shoppingCartDTO.setUserId(shoppingCart.getUser().getId());
            shoppingCartDTO.setTotal(shoppingCart.getTotal());
            shoppingCartDTO.setCartItems(cartItemDTOS);
        return shoppingCartDTO;
    }

}
