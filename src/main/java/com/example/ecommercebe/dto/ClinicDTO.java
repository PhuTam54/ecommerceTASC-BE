package com.example.ecommercebe.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDTO {
    private Long id;
    private String clinicName;
    private String address;
    private String phone;
    private String email;
    private String openingHours;
    private String closingHours;
}
