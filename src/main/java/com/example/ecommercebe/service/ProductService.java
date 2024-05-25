package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductByName(String name);
    void addProduct(ProductDTO productDTO);
    void updateProduct(long id, ProductDTO updatedProductDTO);
    void deleteProduct(long id);
}
