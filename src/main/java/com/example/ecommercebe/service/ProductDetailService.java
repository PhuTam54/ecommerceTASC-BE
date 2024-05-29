package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ProductDetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDetailService {
    ProductDetailDTO getProductDetailById(long id);
    void addProductDetail(ProductDetailDTO productDetailDTO);
    void updateProductDetail(long id, ProductDetailDTO updatedProductDetailDTO);
    void deleteProductDetail(long id);
}
