package com.example.ecommercebe.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StockId implements Serializable {

    @Column(name = "ProductID")
    private Long productId;

    @Column(name = "ClinicID")
    private Long clinicId;

}