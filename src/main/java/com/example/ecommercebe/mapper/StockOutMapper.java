package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.StockOutDTO;
import com.example.ecommercebe.entities.StockOut;
import com.example.ecommercebe.entities.StockId;
import com.example.ecommercebe.repositories.ClinicRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockOutMapper {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    public StockOutDTO toDTO(StockOut stockOut) {
        StockOutDTO stockOutDTO = new StockOutDTO();
        stockOutDTO.setQuantity(stockOut.getQuantity());
        stockOutDTO.setDateout(stockOut.getDateOut());
        stockOutDTO.setReason(stockOut.getReason());
        stockOutDTO.setProduct_id(stockOut.getProduct().getId());
        stockOutDTO.setClinic_id(stockOut.getClinic().getId());
        return stockOutDTO;
    }

    public StockOut toEntity(StockOutDTO stockOutDTO){
        StockOut stockOut = new StockOut();
        stockOut.setQuantity(stockOutDTO.getQuantity());
        stockOut.setDateOut(stockOutDTO.getDateout());
        stockOut.setReason(stockOutDTO.getReason());
        stockOut.setProduct(productRepository.findById(stockOutDTO.getProduct_id()).orElse(null));
        stockOut.setClinic(clinicRepository.findById(stockOutDTO.getClinic_id()).orElse(null));
        StockId id = new StockId(stockOutDTO.getProduct_id(),stockOutDTO.getClinic_id());
        return stockOut;
    }
}
