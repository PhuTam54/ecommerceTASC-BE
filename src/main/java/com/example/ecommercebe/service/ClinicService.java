package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ClinicDTO;
import com.example.ecommercebe.entities.Clinic;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClinicService {
    Page<Clinic> getAllClinics(Pageable pageable);
    Clinic getClinicById(long id);
    Page<Clinic> getClinicByAddress (String address, Pageable pageable);
    void addClinic (ClinicDTO clinicDTO);
    void updateClinic (long id, ClinicDTO clinicDTO);
    void deleteClinic (long id);
}
