package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.StockOutDTO;
import com.example.ecommercebe.entities.StockOut;

import java.util.List;

public interface StockOutService {
    List<StockOutDTO> getAllStockOutByProductId(long productId);
    List<StockOutDTO> getAllStockOutByClinicId(long clinicId);
    void  addStockOut(StockOutDTO stockOutDTO);
    void updateStockOut(Long productId, Long clinicId, StockOutDTO stockOutDTO);
    void deleteStockOut(Long productId, Long clinicId);
}
