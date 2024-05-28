package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ClinicDTO;
import com.example.ecommercebe.entities.Clinic;
import com.example.ecommercebe.helper.mapper.ClinicMapper;
import com.example.ecommercebe.repositories.ClinicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClinicService  {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicMapper clinicMapper;

    public Clinic getClinicById (long id) {
        return clinicRepository.findById(id).orElse(null);
    }

    public List<Clinic> getAllClinic() {
        List<ClinicDTO> clinicDTOS = new ArrayList<>();
        return clinicRepository.findAll();
    }

    public List<Clinic> getClinicByAddress (String address) {
        return clinicRepository.findByAddress(address);
    }

    public void addClinic (ClinicDTO clinicDTO) {
        Clinic clinic = new Clinic();
        clinic.setClinicName(clinicDTO.getClinicName());
        clinic.setAddress(clinicDTO.getEmail());
        clinic.setPhone(clinicDTO.getPhone());
        clinic.setEmail(clinicDTO.getEmail());
        clinic.setOpeningHours(clinicDTO.getOpeningHours());
        clinic.setClosingHours(clinicDTO.getClosingHours());
        clinicRepository.save(clinic);
    }

    public void updateClinic (long id, ClinicDTO clinicDTO) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(id);

        if (optionalClinic.isPresent()) {
            Clinic clinic = optionalClinic.get();
            ClinicMapper.INSTANCE.updateEntityFromDto(clinicDTO, clinic);

            clinicRepository.save(clinic);
        } else {
            throw new EntityNotFoundException("Clinic not found with id: " + id);
        }
    }


    @Transactional
    public void deleteClinic(long id) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(id);

        if (optionalClinic.isPresent()) {
            clinicRepository.delete(optionalClinic.get());
        } else {
            throw new EntityNotFoundException("Clinic not found with id: " + id);
        }
    }

    public ClinicDTO convertToDto(Clinic clinic) {
        return clinicMapper.clinicToClinicDTO(clinic);
    }

    public Clinic convertToEntity(ClinicDTO clinicDTO) {
        return clinicMapper.clinicDTOToClinic(clinicDTO);
    }
}
