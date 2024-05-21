package com.example.ecommercebe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CartItemId implements Serializable {
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "shopping_cart_id")
    private Integer shoppingCartId;
}
