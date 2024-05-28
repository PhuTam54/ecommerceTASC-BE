package com.example.ecommercebe.service.impl;

import com.example.ecommercebe.dto.ClinicDTO;
import com.example.ecommercebe.entities.Clinic;
import com.example.ecommercebe.mapper.ClinicMapper;
import com.example.ecommercebe.repositories.ClinicRepository;
import com.example.ecommercebe.service.ClinicService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicMapper clinicMapper;

    @Override
    public Clinic getClinicById (long id) {
        return clinicRepository.findById(id).orElse(null);
    }

    @Override
    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    @Override
    public List<Clinic> getClinicByAddress (String address) {
        return clinicRepository.findClinicsByAddress(address);
    }

    @Override
    public void addClinic (ClinicDTO clinicDTO) {
        Clinic clinic = clinicMapper.clinicDTOToClinic(clinicDTO);
        clinicRepository.save(clinic);
    }

    @Override
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


    @Override
    @Transactional
    public void deleteClinic(long id) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(id);

        if (optionalClinic.isPresent()) {
            clinicRepository.delete(optionalClinic.get());
        } else {
            throw new EntityNotFoundException("Clinic not found with id: " + id);
        }
    }
}
