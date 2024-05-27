package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.StockInDTO;

import com.example.ecommercebe.entities.StockIn;
import com.example.ecommercebe.repositories.ClinicRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockInMapper {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    public StockInDTO toDTO(StockIn stockIn) {
        StockInDTO stockInDTO = new StockInDTO();
        stockInDTO.setId(stockIn.getId());
        stockInDTO.setQuantity(stockIn.getQuantity());
        stockInDTO.setDateIn(stockIn.getDateIn());
        stockInDTO.setSupplier(stockIn.getSupplier());
        stockInDTO.setExpiryDate(stockIn.getExpiryDate());
        stockInDTO.setManufactureDate(stockIn.getManufactureDate());
        stockInDTO.setProduct_id(stockIn.getProduct().getId());
        stockInDTO.setClinic_id(stockIn.getClinic().getId());
        stockInDTO.setStatus(stockIn.getStatus());
        return stockInDTO;
    }

    public StockIn toEntity(StockInDTO stockInDTO) {
        StockIn stockIn = new StockIn();
        stockIn.setId(stockInDTO.getId());
        stockIn.setQuantity(stockInDTO.getQuantity());
        stockIn.setDateIn(stockInDTO.getDateIn());
        stockIn.setSupplier(stockInDTO.getSupplier());
        stockIn.setExpiryDate(stockInDTO.getExpiryDate());
        stockIn.setManufactureDate(stockInDTO.getManufactureDate());
        stockIn.setProduct(productRepository.findById(stockInDTO.getProduct_id()).orElse(null));
        stockIn.setClinic(clinicRepository.findById(stockInDTO.getProduct_id()).orElse(null));
        stockIn.setStatus(stockInDTO.getStatus());
        return stockIn;
    }


}

//    private long productId;
//    private long clinicId;
//    private int quantity;
//    private LocalDateTime dateIn;
//    private String supplier;
//    private LocalDateTime manufactureDate;
//    private LocalDateTime expiryDate;