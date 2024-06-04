package com.example.ecommercebe.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CartItemId implements Serializable {
    @Column(name = "product_id")
    private long productId;
    @Column(name = "clinic_id")
    private long clinicId;
    @Column(name = "shopping_cart_id")
    private Integer shoppingCartId;
}
