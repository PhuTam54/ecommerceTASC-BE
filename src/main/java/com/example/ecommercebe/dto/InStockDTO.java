package com.example.ecommercebe.dto;

import com.example.ecommercebe.statics.enums.InStockStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InStockDTO {
    private long product_id;
    private long clinic_id;
    private long stockQuantity;
    private LocalDateTime lastUpdate;
    private InStockStatus statusStock;
}
