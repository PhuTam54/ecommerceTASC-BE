package com.example.ecommercebe.entities;

import com.example.ecommercebe.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clinics")
public class Clinic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ClinicName", unique = true, nullable = false)
    private String clinicName;
    @Column(name = "Address")
    private String address;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "opening_hours")
    private String openingHours;
    @Column(name = "closing_hours")
    private String closingHours;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockIn> stockIn;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockOut> stockOut;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InStock>  inStock;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ShoppingCart> shoppingCarts;

    @OneToOne
    private CartItem cartItem;
}
