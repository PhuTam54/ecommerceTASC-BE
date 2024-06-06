package com.example.ecommercebe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long productId;
    private Long clinicId;
    private Integer shoppingCartId;
    private Integer quantity;
    private BigDecimal total;
}
