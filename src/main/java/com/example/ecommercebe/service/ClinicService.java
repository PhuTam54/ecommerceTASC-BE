package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ClinicDTO;
import com.example.ecommercebe.entities.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClinicService {
    List<Clinic> getAllClinics();
    Clinic getClinicById(long id);
    List<Clinic> getClinicByAddress (String address);
    void addClinic (ClinicDTO clinicDTO);
    void updateClinic (long id, ClinicDTO clinicDTO);
    void deleteClinic (long id);
}
