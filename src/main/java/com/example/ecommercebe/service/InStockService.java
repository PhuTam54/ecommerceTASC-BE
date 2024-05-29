package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.InStockDTO;
import com.example.ecommercebe.statics.enums.InStockStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InStockService {
    List<InStockDTO> getInStockByProductId(long productId);
    List<InStockDTO> getInStockByClinicId(long clinicId);
    List<InStockDTO> getInStockByProducIdAndClinicId(long productId,long clinicId);
    void updateInStock(long productId, long clinicId);
    InStockStatus determineStockStatus(long quantity);
    void addInStock(long product_id, long clinic_id);
}
