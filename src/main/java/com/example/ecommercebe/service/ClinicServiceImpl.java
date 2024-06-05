package com.example.ecommercebe.service;

import com.example.ecommercebe.controller.ClinicController;
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

import static java.time.LocalDateTime.now;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicMapper clinicMapper;

    @Override
    public ClinicDTO getClinicById (long id) {
        Clinic clinic = clinicRepository.findById(id).orElse(null);
        if(clinic==null){
            throw new RuntimeException("Can not find clinic with id " + id);
        }
        return clinicMapper.clinicToClinicDTO(clinic);
    }

    @Override
    public Page<ClinicDTO> getAllClinics(Pageable pageable) {
        return clinicRepository.findByDeletedAtIsNull(pageable).map(clinicMapper::clinicToClinicDTO);
    }

    @Override
    public Page<ClinicDTO> getClinicByAddress (String address, Pageable pageable) {
        return clinicRepository.findClinicsByAddress(address, pageable).map(clinicMapper::clinicToClinicDTO);
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

        Optional<Clinic> clinicByName = clinicRepository.findByClinicName(clinicDTO.getClinicName());
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
        if(clinicDTO.getClinicName() != null){
            optionalClinic.get().setClinicName(clinicDTO.getClinicName());
        }
        if(clinicDTO.getAddress() != null){
            optionalClinic.get().setAddress(clinicDTO.getAddress());
        }
        if(clinicDTO.getPhone() != null){
            optionalClinic.get().setPhone(clinicDTO.getPhone());
        }
        if(clinicDTO.getEmail() != null){
            optionalClinic.get().setEmail(clinicDTO.getEmail());
        }
        clinicRepository.save(optionalClinic.get());
    }


    @Override
    public void deleteClinic(long id) {
        Clinic optionalClinic = clinicRepository.findById(id).orElse(null);

        if (optionalClinic == null) {
            throw new EntityNotFoundException("Clinic not found with id: " + id);
        }
        clinicRepository.delete(optionalClinic);
    }

    @Override
    public void moveToTrash(long id) {
        Clinic optionalClinic = clinicRepository.findById(id).orElse(null);

        if (optionalClinic == null) {
            throw new EntityNotFoundException("Clinic not found with id: " + id);
        }
        optionalClinic.setDeletedAt(now());
        clinicRepository.save(optionalClinic);
    }

    @Override
    public Page<ClinicDTO> getInTrash(Pageable pageable) {
        return clinicRepository.findByDeletedAtIsNotNull(pageable).map(clinicMapper::clinicToClinicDTO);
    }
}
