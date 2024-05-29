package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ClinicDTO;
import com.example.ecommercebe.entities.Clinic;
import com.example.ecommercebe.mapper.ClinicMapper;
import com.example.ecommercebe.repositories.ClinicRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public Page<Clinic> getAllClinics(Pageable pageable) {
        return clinicRepository.findAll(pageable);
    }

    @Override
    public Page<Clinic> getClinicByAddress (String address, Pageable pageable) {
        return clinicRepository.findClinicsByAddress(address, pageable);
    }

    @Override
    public void addClinic (ClinicDTO clinicDTO) {

        if (clinicDTO == null) {
            throw new NullPointerException("ClinicDTO can't be null");
        }

        Optional<Clinic> clinicByEmail = clinicRepository.findByEmail(clinicDTO.getEmail());
        if (clinicByEmail.isPresent()) {
            throw new IllegalArgumentException("Clinic email already in use");
        }

        Optional<Clinic> clinicByName = clinicRepository.findByName(clinicDTO.getClinicName());
        if (clinicByName.isPresent()) {
            throw new IllegalArgumentException("Clinic name already in use");
        }

        Optional<Clinic> clinicByPhone = clinicRepository.findByPhone(clinicDTO.getPhone());
        if (clinicByPhone.isPresent()) {
            throw new IllegalArgumentException("Clinic phone already in use");
        }

        Clinic clinic = clinicMapper.clinicDTOToClinic(clinicDTO);
        clinicRepository.save(clinic);
    }

    @Override
    public void updateClinic (long id, ClinicDTO clinicDTO) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(id);

        if (!optionalClinic.isPresent()) {
            throw new EntityNotFoundException("Clinic not found with id: " + id);
        }

        Clinic clinic = optionalClinic.get();
        ClinicMapper.INSTANCE.updateEntityFromDto(clinicDTO, clinic);

        clinicRepository.save(clinic);
    }


    @Override
    @Transactional
    public void deleteClinic(long id) {
        Optional<Clinic> optionalClinic = clinicRepository.findById(id);

        if (!optionalClinic.isPresent()) {
            throw new EntityNotFoundException("Clinic not found with id: " + id);
        }

        clinicRepository.delete(optionalClinic.get());
    }
}
