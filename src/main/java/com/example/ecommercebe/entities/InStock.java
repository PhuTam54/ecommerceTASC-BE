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
@Table(name = "InStock")
public class InStock {

    @EmbeddedId
    private StockId stockId;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductId",referencedColumnName = "id")
    private Product product;

    @MapsId("clinicId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClinicID",referencedColumnName = "id")
    private Clinic clinic;

    @Column(name = "StockQuantity")
    private long stockQuantity;

    @Column(name = "LastUpdated")
    private LocalDateTime lastUpdated;

    @Column(name = "StockStatus")
    private InStockStatus stockStatus;
}
