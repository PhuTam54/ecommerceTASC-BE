package com.example.ecommercebe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    private String detail;
    private BigDecimal price;
    private String manufacturer;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    private List<Feedback> feedback;

    @OneToMany(mappedBy = "product")
    private Set<FavoriteProducts> favoriteProducts;
}
