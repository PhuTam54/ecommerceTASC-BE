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
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
//Serializable => serialize + deserialize file io
public class OrderDetailId implements Serializable {
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "product_id")
    private Integer productId;
}
