package com.example.ecommercebe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDTO {
    private long ProductId;
    private String Ingredient;
    private String Usages;
    private String UsageInstructions;
    private String Description;
    private String SideEffects;
    private String Precautions;
    private String Storage;
}