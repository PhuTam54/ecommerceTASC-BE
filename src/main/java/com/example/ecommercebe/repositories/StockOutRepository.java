package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.StockOut;
import com.example.ecommercebe.entities.StockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StockOutRepository extends JpaRepository<StockOut, StockId>, JpaSpecificationExecutor<StockOut>{
    List<StockOut> findStockOutByClinic(long clinicId);
    List<StockOut> findStockOutByProduct(long productId);
}