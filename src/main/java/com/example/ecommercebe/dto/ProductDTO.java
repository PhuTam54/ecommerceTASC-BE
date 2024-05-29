package com.example.ecommercebe.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    private long categoryId;
    private MultipartFile productImage;
}
