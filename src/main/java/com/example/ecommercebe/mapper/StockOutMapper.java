package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.StockOutDTO;
import com.example.ecommercebe.entities.StockOut;
import com.example.ecommercebe.repositories.ClinicRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.statics.enums.Reason;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockOutMapper {
    private final ProductRepository productRepository;
    private final ClinicRepository clinicRepository;

    public StockOutDTO toDTO(StockOut stockOut) {
        StockOutDTO stockOutDTO = new StockOutDTO();
        stockOutDTO.setId(stockOut.getId());
        stockOutDTO.setQuantity(stockOut.getQuantity());
        stockOutDTO.setDateOut(stockOut.getDateOut());
        stockOutDTO.setReason(String.valueOf(stockOut.getReason()));
        stockOutDTO.setProduct_id(stockOut.getProduct().getId());
        stockOutDTO.setClinic_id(stockOut.getClinic().getId());
        return stockOutDTO;
    }

    public StockOut toEntity(StockOutDTO stockOutDTO){
        StockOut stockOut = new StockOut();
        stockOut.setId(stockOutDTO.getId());
        stockOut.setQuantity(stockOutDTO.getQuantity());
        stockOut.setDateOut(stockOutDTO.getDateOut());
        stockOut.setReason(Reason.valueOf(stockOutDTO.getReason()));
        stockOut.setProduct(productRepository.findById(stockOutDTO.getProduct_id()).orElse(null));
        stockOut.setClinic(clinicRepository.findById(stockOutDTO.getClinic_id()).orElse(null));
        return stockOut;
    }
}
