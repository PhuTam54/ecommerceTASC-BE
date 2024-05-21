package com.example.ecommercebe.obj;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
class DTO implements Serializable {
    private int customer_id;
    private int order_id;
    private int product_id;

    public DTO() {}

    public DTO(int customer_id, int order_id, int product_id) {
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.product_id = product_id;
    }
}

@Getter
@Setter
@Entity
@IdClass(DTO.class)
public class ResultDTO {
    @Id
    private int customer_id;
    @Id
    private int order_id;
    @Id
    private int product_id;
    private String customerName;
    private String customerEmail;
    private LocalDate orderDate;
    private String productName;
    private Double productPrice;

    public ResultDTO() {}

    public ResultDTO(int customer_id, int order_id, int product_id, String customerName, String customerEmail, LocalDate orderDate, String productName, Double productPrice) {
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.orderDate = orderDate;
        this.productName = productName;
        this.productPrice = productPrice;
    }
}