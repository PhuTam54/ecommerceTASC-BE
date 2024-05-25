package com.example.ecommercebe.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    @NotNull(message = "Name is mandatory")
    private String name;
    private String description;
    private String detail;
    private double price;
    private String Manufacturer;
    private long categoryId;
}
