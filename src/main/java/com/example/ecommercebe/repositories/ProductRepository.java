package com.example.ecommercebe.repositories;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findByNameAndDeletedAtIsNull(Pageable pageable,String name);
    Page<Product> findByCategoryAndDeletedAtIsNull(Pageable pageable,Category category);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByDeletedAtIsNotNull(Pageable pageable);
    Page<Product> findByDeletedAtIsNull(Pageable pageable);
}