package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.InStockDTO;
import com.example.ecommercebe.entities.InStock;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.entities.StockId;
import com.example.ecommercebe.repositories.ClinicRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
