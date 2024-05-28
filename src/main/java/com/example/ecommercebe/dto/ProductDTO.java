package com.example.ecommercebe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {
    private long Id;
    @NotNull(message = "Name is mandatory")
    private String name;
    private String description;
    private String detail;
    private double price;
    private String Manufacturer;
    private Integer categoryId;
}
