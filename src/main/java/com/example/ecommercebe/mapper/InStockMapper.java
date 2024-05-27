package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.InStockDTO;
import com.example.ecommercebe.entities.InStock;
import com.example.ecommercebe.entities.StockId;

public class InStockMapper {
    public static InStockDTO toDTO(InStock inStock){
        InStockDTO inStockDTO = new InStockDTO();
        inStockDTO.setProduct_id(inStock.getId().getProductId());
        inStockDTO.setClinic_id(inStock.getId().getClinicId());
        inStockDTO.setStockQuantity(inStock.getStockQuantity());
        inStockDTO.setLastUpdate(inStock.getLastUpdated());
        inStockDTO.setStatusStock(inStock.getStockStatus());
        return inStockDTO;
    }

    public static InStock toEntity(InStockDTO inStockDTO){
        InStock inStock = new InStock();
        inStock.setId(new StockId(inStockDTO.getProduct_id(), inStockDTO.getClinic_id()));
        inStock.setStockStatus(inStockDTO.getStatusStock());
        inStock.setStockQuantity(inStockDTO.getStockQuantity());
        inStock.setLastUpdated(inStockDTO.getLastUpdate());
        return inStock;
    }
}
