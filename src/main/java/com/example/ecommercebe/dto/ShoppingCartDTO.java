package com.example.ecommercebe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private Integer id;
    private Long userId;
    private BigDecimal total;
    private Set<CartItemDTO> cartItems;
}
