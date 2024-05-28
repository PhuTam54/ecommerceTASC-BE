//package com.example.ecommercebe.controller;
//
//import com.example.ecommercebe.dto.ClinicDTO;
//import com.example.ecommercebe.entities.Clinic;
//import com.example.ecommercebe.exception.EntityNotFoundException;
//import com.example.ecommercebe.service.ClinicService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/clinics")
//public class ClinicController {
//
//    @Autowired
//    private ClinicService clinicService;
//
//    @GetMapping
//    public ResponseEntity<List<Clinic>> getAllClinics() {
//        List<Clinic> clinics = clinicService.getAllClinic();
//        return ResponseEntity.ok(clinics);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Clinic> getClinicById(@PathVariable long id) {
//        Clinic clinic = clinicService.getClinicById(id);
//        if (clinic != null) {
//            return ResponseEntity.ok(clinic);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<Clinic>> getClinicsByAddress(@RequestParam String address) {
//        List<Clinic> clinics = clinicService.getClinicByAddress(address);
//        return ResponseEntity.ok(clinics);
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> addClinic(@RequestBody ClinicDTO clinicDTO) {
//        clinicService.addClinic(clinicDTO);
//        return ResponseEntity.status(201).build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateClinic(@PathVariable long id, @RequestBody ClinicDTO clinicDTO) {
//        try {
//            clinicService.updateClinic(id, clinicDTO);
//            return ResponseEntity.ok().build();
//        } catch (EntityNotFoundException ex) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteClinic(@PathVariable long id) {
//        try {
//            clinicService.deleteClinic(id);
//            return ResponseEntity.noContent().build();
//        }catch (EntityNotFoundException ex) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
