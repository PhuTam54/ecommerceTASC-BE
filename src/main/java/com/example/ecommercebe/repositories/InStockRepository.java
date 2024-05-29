package com.example.ecommercebe.repositories;


import com.example.ecommercebe.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InStockRepository extends JpaRepository<InStock, Long> {
    List<InStock> findInStockByClinic(Clinic clinic);
    List<InStock> findInStockByProduct(Product product);
    List<InStock> findInStockByProductAndClinic(Product product, Clinic clinic);
}
