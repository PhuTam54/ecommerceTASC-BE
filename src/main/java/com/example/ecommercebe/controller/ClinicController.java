package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.ClinicDTO;
import com.example.ecommercebe.entities.Clinic;
import com.example.ecommercebe.exception.EntityNotFoundException;
import com.example.ecommercebe.service.ClinicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Clinic", description = "Clinic Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping
    public ResponseEntity<Page<Clinic>> getAllClinics(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Clinic> clinics = clinicService.getAllClinics(pageable);
        return ResponseEntity.ok(clinics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinic> getClinicById(@PathVariable long id) {
        Clinic clinic = clinicService.getClinicById(id);
        if (clinic != null) {
            return ResponseEntity.ok(clinic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Clinic>> getClinicsByAddress(
            @RequestParam String address,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Clinic> clinics = clinicService.getClinicByAddress(address, pageable);
        return ResponseEntity.ok(clinics);
    }

    @PostMapping
    public ResponseEntity<Void> addClinic(@RequestBody ClinicDTO clinicDTO) {
        clinicService.addClinic(clinicDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClinic(@PathVariable long id, @RequestBody ClinicDTO clinicDTO) {
        try {
            clinicService.updateClinic(id, clinicDTO);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable long id) {
        try {
            clinicService.deleteClinic(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
