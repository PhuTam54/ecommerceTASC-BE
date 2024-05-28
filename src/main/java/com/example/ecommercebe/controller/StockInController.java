package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.StockInDTO;

import com.example.ecommercebe.exception.NotFoundException;
import com.example.ecommercebe.service.StockInService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Stock in", description = "Stock in Controller")
@CrossOrigin
@RestController
@RequestMapping("/stockIn")
public class StockInController {

    @Autowired
    private StockInService stockInService;

    @GetMapping("/product/{id}")
    public ResponseEntity<List<StockInDTO>> getAllStockInByProduct(@PathVariable long productId) {
        List<StockInDTO> stockInDTO = stockInService.getAllStockInByProductId(productId);
        if (stockInDTO == null) {
            throw new NotFoundException("Stock not found with ProductId: " + productId);
        }
        return ResponseEntity.ok(stockInDTO);
    }

    @GetMapping("/clinic/{id}")
    public ResponseEntity<List<StockInDTO>> getAllStockInByClinic(@PathVariable long clinicId) {
        List<StockInDTO> stockInDTO = stockInService.getAllStockInByClinicId(clinicId);
        if (stockInDTO == null) {
            throw new NotFoundException("Stock not found with ClinicId: " + clinicId);
        }
        return ResponseEntity.ok(stockInDTO);
    }

    @GetMapping("/{productId}/{clinicId}")
    public ResponseEntity<List<StockInDTO>> getAllStockInByProductAndClinic(@PathVariable long clinicId,
                                                                              @PathVariable long productId) {
        List<StockInDTO> stockInDTO = stockInService.getAllStockInByProductIdAndClinicId(productId,clinicId);
        if (stockInDTO == null) {
            throw new NotFoundException("Stock not found with ClinicId: " + clinicId);
        }
        return ResponseEntity.ok(stockInDTO);
    }

    @PostMapping("/add/")
    public ResponseEntity<?> addStockIn(@Valid @RequestBody StockInDTO stockInDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        stockInService.addStockIn(stockInDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStockIn(@PathVariable long id,
                                            @Valid @RequestBody StockInDTO stockInDTO, BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        stockInService.updateStockIn(id,stockInDTO);
        return new ResponseEntity<>(HttpStatus.UPGRADE_REQUIRED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStockIn(@PathVariable long id, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        stockInService.deleteStockIn(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
