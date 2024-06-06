package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.StockOutDTO;

import java.util.List;

public interface StockOutService {
    List<StockOutDTO> getAllStockOutByProductId(long productId);
    List<StockOutDTO> getAllStockOutByClinicId(long clinicId);
    List<StockOutDTO> getAllStockOutByProductIdAndClinicId(long productId, long clinicId);
    void  addStockOut(StockOutDTO stockOutDTO);
    void updateStockOut(long id, StockOutDTO stockOutDTO);
    void deleteStockOut(long id);
}
