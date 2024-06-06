package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface ProductService {
    Page<ProductDTO> getAllProducts(Pageable pageable);
    Page<ProductDTO> getProductByName(Pageable pageable,String name);
    Page<ProductDTO> findByCategory(Pageable pageable,Category category);
    void addProduct(ProductDTO productDTO);
    void updateProduct(long id, ProductDTO updatedProductDTO);
    void deleteProduct(long id);
    void moveToTrash(Long id);
    Page<ProductDTO> getInTrash(Pageable pageable);
}
