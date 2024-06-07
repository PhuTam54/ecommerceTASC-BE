package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ClinicDTO;
import com.example.ecommercebe.entities.Clinic;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClinicService {
    Page<ClinicDTO> getAllClinics(Pageable pageable);
    ClinicDTO getClinicById(long id);
    Page<ClinicDTO> getClinicByAddress (String address, Pageable pageable);
    void addClinic (ClinicDTO clinicDTO);
    void updateClinic (long id, ClinicDTO clinicDTO);
    void deleteClinic (long id);
    void moveToTrash(long id);
    Page<ClinicDTO> getInTrash(Pageable pageable);
}
