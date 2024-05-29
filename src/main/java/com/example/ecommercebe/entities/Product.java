package com.example.ecommercebe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "detail")
    private String detail;
    @Column(name = "price")
    private double price;
    @Column(name = "manufacturer")
    private String manufacturer;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<FavoriteProducts> favoriteProducts;
  
    @OneToMany(mappedBy = "product")
    private List<StockOut> stockOuts;
  
    @OneToMany(mappedBy = "product")
    private List<StockIn> stockIns;
  
    @OneToMany(mappedBy = "product")
    private List<InStock> inStocks;
  
    @OneToMany(mappedBy = "product")
    private List<Feedback> feedbacks;
  
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductDetail productDetail;

}
