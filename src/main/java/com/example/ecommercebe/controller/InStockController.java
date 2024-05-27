package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.InStockDTO;
import com.example.ecommercebe.exception.NotFoundException;
import com.example.ecommercebe.service.InStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inStock")
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

    @GetMapping("/{productId}/{clinicId}")
    public ResponseEntity<?> getAllInStockByProductIdAndClinicId(@PathVariable long productId,@PathVariable long clinicId){
        List<InStockDTO> inStockDTO = inStockService.getInStockByProducIdAndClinicId(productId, clinicId);
        if(inStockDTO.isEmpty()){
            throw new NotFoundException("InStock not found with ProductId" + productId + "and ClinicId" + clinicId);
        }
        return ResponseEntity.ok(inStockDTO);
    }
    @PutMapping("/update/{productId}/{clinicId}")
    public ResponseEntity<?> updateInStock(@PathVariable long productId, @PathVariable long clinicId){
        inStockService.updateInStock(productId, clinicId);
        return ResponseEntity.ok(HttpStatus.UPGRADE_REQUIRED);
    }
}
