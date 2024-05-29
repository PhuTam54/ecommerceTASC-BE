package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.InStockDTO;
import com.example.ecommercebe.entities.*;
import com.example.ecommercebe.mapper.InStockMapper;
import com.example.ecommercebe.repositories.*;
import com.example.ecommercebe.statics.enums.InStockStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InStockServiceImpl implements InStockService{

    @Autowired
    private InStockRepository inStockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private StockInRepository stockInRepository;

    @Autowired
    private StockOutRepository stockOutRepository;

    @Override
    public List<InStockDTO> getInStockByProductId(long productId) {
        Optional<Product> product = productRepository.findById(productId);
        Product product1;
        if(product.isPresent()) {
            product1 = product.get();
        } else throw new RuntimeException("Không tìm thấy InStock với product_id" + productId);
        List<InStock> inStocks = inStockRepository.findInStockByProduct(product1);
        return inStocks.stream()
                .map(InStockMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InStockDTO> getInStockByClinicId(long clinicId) {
        Optional<Clinic> clinic = clinicRepository.findById(clinicId);
        Clinic clinic1;
        if(clinic.isPresent()) {
            clinic1 = clinic.get();
        } else throw new RuntimeException("Không tìm thấy InStock với clinic_id" + clinicId);
        List<InStock> inStocks = inStockRepository.findInStockByClinic(clinic1);
        return inStocks.stream()
                .map(InStockMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InStockDTO> getInStockByProducIdAndClinicId(long productId, long clinicId) {
        Optional<Product> product = productRepository.findById(productId);
        Product product1;
        if(product.isPresent()) {
            product1 = product.get();
        } else throw new RuntimeException("Không tìm thấy InStock với product_id" + productId);
        Optional<Clinic> clinic = clinicRepository.findById(clinicId);
        Clinic clinic1;
        if(clinic.isPresent()) {
            clinic1 = clinic.get();
        } else throw new RuntimeException("Không tìm thấy InStock với clinic_id" + clinicId);
        List<InStock> inStocks = inStockRepository.findInStockByProductAndClinic(product1,clinic1);
        return inStocks.stream()
            .map(InStockMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void updateInStock(long productId, long clinicId) {
        long quantityStockOut = 0;
        long quantityStockIn = 0;
        long quantityInStock ;
        Optional<Product> product = productRepository.findById(productId);
        Product product1;
        if(product.isPresent()) {
            product1 = product.get();
        } else throw new RuntimeException("Không tìm thấy InStock với product_id" + productId);
        Optional<Clinic> clinic = clinicRepository.findById(clinicId);
        Clinic clinic1;
        if(clinic.isPresent()) {
            clinic1 = clinic.get();
        } else throw new RuntimeException("Không tìm thấy InStock với clinic_id" + clinicId);
        List<InStock> inStocks = inStockRepository.findInStockByProductAndClinic(product1,clinic1);
        if(inStocks.isEmpty()){
            addInStock(productId, clinicId);
        }
        List<StockOut> stockOut = stockOutRepository.findStockOutByProductAndClinic(product1,clinic1);
        if(!stockOut.isEmpty()){
            for (StockOut stockOut1: stockOut) {
                quantityStockOut += stockOut1.getQuantity();
            }
        }
        List<StockIn> stockIn = stockInRepository.findStockInByProductAndClinic(product1,clinic1);
        if(!stockIn.isEmpty()){
            for (StockIn stockIn1: stockIn) {
                quantityStockOut += stockIn1.getQuantity();
            }
        }
        List<InStock> inStock1 = inStockRepository.findInStockByProductAndClinic(product1,clinic1);
        InStock inStock = inStock1.get(0);
        quantityInStock = quantityStockIn - quantityStockOut;
        inStock.setStockQuantity(quantityInStock);
        inStock.setLastUpdated(LocalDateTime.now());
        inStock.setStockStatus(determineStockStatus(quantityInStock));
        inStockRepository.save(inStock);
    }

    @Override
    public InStockStatus determineStockStatus(long quantity) {
        if (quantity <= 0) {
            return InStockStatus.OUT_OF_STOCK;
        } else if (quantity < 100) {
            return InStockStatus.RUNNING_LOW;
        } else {
            return InStockStatus.IN_STOCK;
        }
    }

    public void addInStock(long product_id, long clinic_id){
//        StockId stockId = new StockId();
//        stockId.setProductId(product_id);
//        stockId.setClinicId(clinic_id);
        InStock inStock = new InStock();

        inStock.setStockQuantity(0);
        Optional<Product> product = productRepository.findById(product_id);
        Product product1;
        if(product.isPresent()) {
            product1 = product.get();
        } else throw new RuntimeException("Không tìm thấy InStock với product_id" + product_id);

        Optional<Clinic> clinic = clinicRepository.findById(clinic_id);
        Clinic clinic1;
        if(clinic.isPresent()) {
            clinic1 = clinic.get();
        } else throw new RuntimeException("Không tìm thấy InStock với clinic_id" + clinic_id);
        inStock.setProduct(product1);
        inStock.setClinic(clinic1);
        inStock.setLastUpdated(LocalDateTime.now());
        inStockRepository.save(inStock);
    }
}
