package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.InStockDTO;
import com.example.ecommercebe.entities.InStock;
import org.springframework.stereotype.Component;


@Component
public class InStockMapper {

    public static InStockDTO toDTO(InStock inStock){
        InStockDTO inStockDTO = new InStockDTO();
        inStockDTO.setProduct_id(inStock.getProduct().getId());
        inStockDTO.setClinic_id(inStock.getClinic().getId());
        inStockDTO.setStockQuantity(inStock.getStockQuantity());
        inStockDTO.setLastUpdate(inStock.getLastUpdated());
        inStockDTO.setStatusStock(inStock.getStockStatus());
        return inStockDTO;
    }

}
