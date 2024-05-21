package com.example.ecommercebe.entity;

import com.example.ecommercebe.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    // custom string id
    private String id;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false,foreignKey = @ForeignKey(name = "user_order_fk"))
    @JsonManagedReference
    private User user;
    private BigDecimal totalPrice;
    private Integer status;
    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<OrderDetail> orderDetails;
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                '}';
    }
}
