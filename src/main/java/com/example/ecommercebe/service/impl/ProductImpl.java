package com.example.ecommercebe.service.Impl;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.service.ProductService;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElse(null);
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.INSTANCE.toEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(long id, ProductDTO updatedProductDTO) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            Product updatedProduct = ProductMapper.INSTANCE.toEntity(updatedProductDTO);
            updatedProduct.setId(existingProduct.getId());
            productRepository.save(updatedProduct);
        }
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
