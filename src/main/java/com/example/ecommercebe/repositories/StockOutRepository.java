package com.example.ecommercebe.repositories;

import com.example.ecommercebe.entities.Clinic;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.entities.StockOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StockOutRepository extends JpaRepository<StockOut, Long>, JpaSpecificationExecutor<StockOut>{
    List<StockOut> findStockOutByClinic(Clinic clinic);
    List<StockOut> findStockOutByProduct(Product product);
    List<StockOut> findStockOutByProductAndClinic(Product product, Clinic clinic);
}