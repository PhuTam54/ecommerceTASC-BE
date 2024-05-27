package com.example.ecommercebe.entities;

import com.example.ecommercebe.statics.enums.InStockStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(StockId.class)
@Table(name = "InStock")
public class InStock {

    @Id
    private Long productId;

    @Id
    private Long clinicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "ProductID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("clinicId")
    @JoinColumn(name = "ClinicID")
    private Clinic clinic;

    @Column(name = "StockQuantity")
    private long stockQuantity;

    @Column(name = "LastUpdated")
    private LocalDateTime lastUpdated;

    @Column(name = "StockStatus")
    private InStockStatus stockStatus;

}
