package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.StockOutDTO;
import com.example.ecommercebe.entities.StockId;
import com.example.ecommercebe.entities.StockOut;
import com.example.ecommercebe.mapper.StockOutMapper;
import com.example.ecommercebe.repositories.StockOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockOutServiceImpl implements StockOutService{

    @Autowired
    private StockOutRepository stockOutRepository;

    @Autowired
    private StockOutMapper stockOutMapper;

    @Override
    public List<StockOutDTO> getAllStockOutByProductId(long productId) {
        List<StockOut> stockOuts = stockOutRepository.findStockOutByProduct(productId);
        return stockOuts.stream()
                .map(stockOutMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockOutDTO> getAllStockOutByClinicId(long clinicId) {
        List<StockOut> stockOuts = stockOutRepository.findStockOutByClinic(clinicId);
        return stockOuts.stream()
                .map(stockOutMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addStockOut(StockOutDTO stockOutDTO) {
        StockOut stockOut = stockOutMapper.toEntity(stockOutDTO);
        stockOutRepository.save(stockOut);
    }

    @Override
    public void updateStockOut(Long productId, Long clinicId, StockOutDTO stockOutDTO) {
        StockId id = new StockId(productId,clinicId);
        Optional<StockOut> stockOut = stockOutRepository.findById(id);
        if (stockOut.isPresent()) {
            StockOut stockOut1 = stockOut.get();
            long i = stockOut1.getQuantity();
            stockOut1.setQuantity(stockOutDTO.getQuantity() + i);
            stockOut1.setReason(stockOutDTO.getReason());
            stockOutRepository.save(stockOut1);
        } else {
            throw new RuntimeException("Không tìm thấy StockOut với productId " + productId + " và clinicId " + clinicId);
        }
    }

    @Override
    public void deleteStockOut(Long productId, Long clinicId) {
        Optional<StockOut> stockOut = stockOutRepository.findById(new StockId(productId,clinicId));
        stockOutRepository.delete(stockOut.get());
    }
}
