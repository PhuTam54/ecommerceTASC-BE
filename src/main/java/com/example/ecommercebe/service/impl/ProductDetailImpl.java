package com.example.ecommercebe.service.impl;

import com.example.ecommercebe.dto.ProductDetailDTO;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.entities.ProductDetail;
import com.example.ecommercebe.mapper.ProductDetailMapper;
import com.example.ecommercebe.repositories.ProductDetailRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductDetailImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDetailDTO getProductDetailById(long ProductId) {
        return productDetailMapper.toDTO(productDetailRepository.findById(ProductId).orElse(null));
    }

    @Override
    public void addProductDetail(ProductDetailDTO productDetailDTO) {
        Optional<Product> product = productRepository.findById(productDetailDTO.getProductId());
        if (product.isPresent()) {
            productDetailRepository.save(productDetailMapper.toEntity(productDetailDTO));
        }
        else{
            throw new RuntimeException("Product not found with id: " + productDetailDTO.getProductId());
        }
    }

    @Override
    public void updateProductDetail(long ProductId, ProductDetailDTO updatedProductDetailDTO) {
        ProductDetail existingProductDetail = productDetailRepository.findById(ProductId).orElse(null);
        if (existingProductDetail != null) {
            ProductDetail updatedProductDetail = productDetailMapper.toEntity(updatedProductDetailDTO);
            updatedProductDetail.setProductId(existingProductDetail.getProductId());
            productDetailRepository.save(updatedProductDetail);
        }
    }

    @Override
    public void deleteProductDetail(long id) {
        productDetailRepository.deleteById(id);
    }
}
