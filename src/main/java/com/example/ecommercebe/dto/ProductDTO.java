package com.example.ecommercebe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDTO {
    @NotNull(message = "Name is mandatory")
    private String name;
    private String description;
    private String detail;
    private double price;
    private String Manufacturer;
    private long categoryId;
    private MultipartFile productImage;
}
