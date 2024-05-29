package com.example.ecommercebe.service.impl;

import com.example.ecommercebe.dto.ProductDetailDTO;
import com.example.ecommercebe.entities.ProductDetail;
import com.example.ecommercebe.mapper.ProductDetailMapper;
import com.example.ecommercebe.repositories.ProductDetailRepository;
import com.example.ecommercebe.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public ProductDetailDTO getProductDetailById(long id) {
        return productDetailMapper.toDTO(productDetailRepository.findById(id).orElse(null));
    }

    @Override
    public void addProductDetail(ProductDetailDTO productDetailDTO) {
        if (productDetailRepository.existsById(productDetailDTO.getProductId())) {
            throw new RuntimeException("Product with id " + productDetailDTO.getProductId() + " already exists.");
        }
        ProductDetail productDetail = productDetailMapper.toEntity(productDetailDTO);
        productDetailRepository.save(productDetail);
    }

    @Override
    public void updateProductDetail(long id, ProductDetailDTO updatedProductDetailDTO) {
        ProductDetail existingProductDetail = productDetailRepository.findById(id).orElse(null);
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
