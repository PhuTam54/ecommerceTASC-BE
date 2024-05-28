package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.StockInDTO;
import com.example.ecommercebe.entities.Clinic;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.entities.StockIn;
import com.example.ecommercebe.mapper.StockInMapper;
import com.example.ecommercebe.repositories.ClinicRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.repositories.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockInServiceImpl implements StockInService{

    @Autowired
    private StockInRepository stockInRepository;

    @Autowired
    private StockInMapper stockInMapper;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<StockInDTO> getAllStockInByProductId(long productId) {
        Optional<Product> product = productRepository.findById(productId);
        Product product1 ;
        if(product.isPresent()) {
            product1 = product.get();
        } else throw new RuntimeException("Không tìm thấy InStock với product_id" + productId);
        List<StockIn> stockIns = stockInRepository.findStockInByProduct(product1);
        return stockIns.stream()
                .map(stockInMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockInDTO> getAllStockInByClinicId(long clinicId) {
        Optional<Clinic> clinic = clinicRepository.findById(clinicId);
        Clinic clinic1;
        if(clinic.isPresent()) {
            clinic1 = clinic.get();
        } else throw new RuntimeException("Không tìm thấy InStock với clinic_id" + clinicId);

        List<StockIn> stockIns = stockInRepository.findStockInByClinic(clinic1);
        return stockIns.stream()
                .map(stockInMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockInDTO> getAllStockInByProductIdAndClinicId(long productId, long clinicId) {
        Optional<Product> product = productRepository.findById(productId);
        Product product1 ;
        if(product.isPresent()) {
            product1 = product.get();
        } else throw new RuntimeException("Không tìm thấy InStock với product_id" + productId);
        Optional<Clinic> clinic = clinicRepository.findById(clinicId);
        Clinic clinic1 ;
        if(clinic.isPresent()) {
            clinic1 = clinic.get();
        } else throw new RuntimeException("Không tìm thấy InStock với clinic_id" + clinicId);

        List<StockIn> stockIns = stockInRepository.findStockInByProductAndClinic(product1, clinic1);
        return stockIns.stream()
                .map(stockInMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addStockIn(StockInDTO stockInDTO) {
        StockIn stockIn = stockInMapper.toEntity(stockInDTO);
        stockInRepository.save(stockIn);
    }

    @Override
    public void updateStockIn(long id, StockInDTO stockInDTO) {
        Optional<StockIn> stockIn = stockInRepository.findById(id);
            StockIn stockIn1;
            if(stockIn.isPresent()) {
                stockIn1 = stockIn.get();
            } else throw new RuntimeException("Không tìm thấy InStock với id" + id);
            if(stockInDTO.getQuantity()!= 0){
                stockIn1.setQuantity(stockInDTO.getQuantity());
            }
            if(stockInDTO.getDateIn() != null){
                stockIn1.setDateIn(stockInDTO.getDateIn());
            }
            if(stockInDTO.getSupplier()!= null){
                stockIn1.setSupplier(stockInDTO.getSupplier());
            }
            if(stockInDTO.getExpiryDate() != null){
                stockIn1.setExpiryDate(stockInDTO.getExpiryDate());
            }
            if(stockInDTO.getManufactureDate() != null){
                stockIn1.setManufactureDate(stockInDTO.getManufactureDate());
            }
            if(stockInDTO.getStatus() !=0){
                stockIn1.setStatus(stockInDTO.getStatus());
            }
            stockInRepository.save(stockIn1);
    }

    @Override
    public void deleteStockIn(long id) {
        Optional<StockIn> stockIn = stockInRepository.findById(id);
        StockIn stockIn1;
        if(stockIn.isPresent()) {
            stockIn1 = stockIn.get();
        } else throw new RuntimeException("Không tìm thấy InStock với id" + id);
        stockInRepository.delete(stockIn1);
    }
}