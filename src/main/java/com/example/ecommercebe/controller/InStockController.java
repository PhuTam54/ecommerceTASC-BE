package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.InStockDTO;
import com.example.ecommercebe.exception.NotFoundException;
import com.example.ecommercebe.service.InStockService;
import com.example.ecommercebe.service.InStockServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "In stock", description = "In stock Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/inStocks")
public class InStockController {
    @Autowired
    private InStockService inStockService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getAllInStockByProductId(@PathVariable long productId){
        List<InStockDTO> inStockDTO = inStockService.getInStockByProductId(productId);
        if (inStockDTO.isEmpty()) {
            throw new NotFoundException("InStock not found with ProductId: " + productId);
        }
        return ResponseEntity.ok(inStockDTO);
    }
    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<?> getAllInStockByClinicId(@PathVariable long clinicId){
        List<InStockDTO> inStockDTO = inStockService.getInStockByClinicId(clinicId);
        if (inStockDTO.isEmpty()) {
            throw new NotFoundException("InStock not found with ProductId: " + clinicId);
        }
        return ResponseEntity.ok(inStockDTO);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllInStockByProductIdAndClinicId(@RequestParam(name = "ProductId") long productId, @RequestParam(name = "ClinicId") long clinicId){
        List<InStockDTO> inStockDTO = inStockService.getInStockByProducIdAndClinicId(productId, clinicId);
        if(inStockDTO.isEmpty()){
            throw new NotFoundException("InStock not found with ProductId" + productId + "and ClinicId" + clinicId);
        }
        return ResponseEntity.ok(inStockDTO);
    }
    @PutMapping("/")
    public ResponseEntity<?> updateInStock(@RequestParam(name = "ProductId") long productId, @RequestParam(name = "ClinicId") long clinicId){
        inStockService.updateInStock(productId, clinicId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
