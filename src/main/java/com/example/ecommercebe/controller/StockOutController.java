package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.StockOutDTO;

import com.example.ecommercebe.exception.NotFoundException;
import com.example.ecommercebe.service.StockOutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stockout")
public class StockOutController {

    @Autowired
    private StockOutService stockOutService;

    @GetMapping("/product/{id}")
    public ResponseEntity<List<StockOutDTO>> getAllStockOutByProduct(@PathVariable long productId) {
        List<StockOutDTO> stockOutDTO = stockOutService.getAllStockOutByProductId(productId);
        if (stockOutDTO == null) {
            throw new NotFoundException("Stock not found with ProductId: " + productId);
        }
        return ResponseEntity.ok(stockOutDTO);
    }

    @GetMapping("/clinic/{id}")
    public ResponseEntity<List<StockOutDTO>> getAllStockOutByClinic(@PathVariable long clinicId) {
        List<StockOutDTO> stockOutDTO = stockOutService.getAllStockOutByProductId(clinicId);
        if (stockOutDTO == null) {
            throw new NotFoundException("Stock not found with ClinicId: " + clinicId);
        }
        return ResponseEntity.ok(stockOutDTO);
    }

    @PostMapping("/add/")
    public ResponseEntity<?> addStockOut(@Valid @RequestBody StockOutDTO stockOutDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        stockOutService.addStockOut(stockOutDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}/{clinicId}")
    public ResponseEntity<?> updateStockOut(@PathVariable long productId,
                                            @PathVariable long clinicId,
                                            @Valid @RequestBody StockOutDTO stockOutDTO, BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        stockOutService.updateStockOut(productId,clinicId,stockOutDTO);
        return new ResponseEntity<>(HttpStatus.UPGRADE_REQUIRED);
    }

    @DeleteMapping("/delete/{productId}/{clinicId}")
    public ResponseEntity<?> deleteStockOut(@PathVariable long productId, @PathVariable long clinicId, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        stockOutService.deleteStockOut(productId, clinicId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
