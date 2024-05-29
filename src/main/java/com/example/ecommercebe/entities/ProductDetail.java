package com.example.ecommercebe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_detail")
public class ProductDetail {
    @Id
    private long ProductId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "ingredient")
    private String Ingredient;
    @Column(name = "usages")
    private String Usages;
    @Column(name = "usage_instructions")
    private String UsageInstructions;
    @Column(name = "description")
    private String Description;
    @Column(name = "side_effects")
    private String SideEffects;
    @Column(name = "precautions")
    private String Precautions;
    @Column(name = "storage")
    private String Storage;
}
