package com.example.ecommercebe.repositories;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByName(String name);
    List<Product> findByCategory(Category category);
}