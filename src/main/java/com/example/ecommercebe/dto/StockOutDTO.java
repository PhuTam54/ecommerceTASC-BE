package com.example.ecommercebe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockOutDTO {
    private long quantity;
    private long product_id;
    private long clinic_id;
    private String reason;
    private LocalDateTime dateout;
}
