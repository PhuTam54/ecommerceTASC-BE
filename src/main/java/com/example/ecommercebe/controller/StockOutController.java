package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.StockOutDTO;

import com.example.ecommercebe.exception.NotFoundException;
import com.example.ecommercebe.service.StockOutService;
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

@Tag(name = "Stock out", description = "Stock out Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/stockOuts")
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
        List<StockOutDTO> stockOutDTO = stockOutService.getAllStockOutByClinicId(clinicId);
        if (stockOutDTO == null) {
            throw new NotFoundException("Stock not found with ClinicId: " + clinicId);
        }
        return ResponseEntity.ok(stockOutDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<StockOutDTO>> getAllStockOutByProductAndClinic(@RequestParam(name = "productId") long clinicId,
                                                                              @RequestParam(name = "clinicId") long productId) {
        List<StockOutDTO> stockOutDTO = stockOutService.getAllStockOutByProductIdAndClinicId(productId,clinicId);
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStockOut(@PathVariable long id,
                                            @Valid @RequestBody StockOutDTO stockOutDTO, BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        stockOutService.updateStockOut(id,stockOutDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStockOut(@PathVariable long id, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        stockOutService.deleteStockOut(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
