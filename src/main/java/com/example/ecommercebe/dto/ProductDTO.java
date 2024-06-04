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
    private String name;
    private String description;
    private String detail;
    private Double price;
    private String Manufacturer;
    private Integer categoryId;
//    private MultipartFile productImage;
}
