package com.example.ecommercebe.repositories;


import com.example.ecommercebe.entities.Clinic;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.entities.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StockInRepository extends JpaRepository<StockIn, long>, JpaSpecificationExecutor<StockIn>{
    List<StockIn> findStockInByClinic(Clinic clinic);
    List<StockIn> findStockInByProduct(Product product);
    List<StockIn> findStockInByProductAndClinic(Product product, Clinic clinic);

}