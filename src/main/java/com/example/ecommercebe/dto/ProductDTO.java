package com.example.ecommercebe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private long Id;
    @NotNull(message = "Name is mandatory")
    private String name;
    private String description;
    private String detail;
    private double price;
    private String Manufacturer;
    @NotNull(message = "Category ID is mandatory")
    private Integer categoryId;
}
