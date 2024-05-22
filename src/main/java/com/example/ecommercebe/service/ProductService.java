package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAll(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    public Collection<Product> findAllActiveProducts(String name) {
        return productRepository.findAllActiveProducts(Sort.by("name"), name);
    }

    public Collection<Product> findAllActiveProductsNative(int status) {
        return productRepository.findAllActiveProductsNative(status);
    }

//    public Collection<ResultDTO> findResultDTOByCustomer(int id) {
//        return productRepository.findResultDTOByCustomer(id);
//    }
}
