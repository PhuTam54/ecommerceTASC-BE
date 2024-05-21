package com.example.ecommercebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {
    @EmbeddedId
    private CartItemId cartItemId = new CartItemId();
    private Integer quantity;
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @MapsId("productId")
    private Product product;
    @ManyToOne
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    @JsonBackReference
    private ShoppingCart shoppingCart;
    private BigDecimal total;

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
