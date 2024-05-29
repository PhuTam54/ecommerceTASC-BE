package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.ProductDetailDTO;
import com.example.ecommercebe.entities.ProductDetail;
import com.example.ecommercebe.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {
    @Autowired
    private ProductRepository productRepository;

    public ProductDetailDTO toDTO(ProductDetail productDetail) {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        productDetailDTO.setProductId(productDetail.getProduct().getId());
        productDetailDTO.setIngredient(productDetail.getIngredient());
        productDetailDTO.setUsage(productDetail.getUsage());
        productDetailDTO.setUsageInstructions(productDetail.getUsageInstructions());
        productDetailDTO.setDescription(productDetail.getDescription());
        productDetailDTO.setSideEffects(productDetail.getSideEffects());
        productDetailDTO.setPrecautions(productDetail.getPrecautions());
        productDetailDTO.setStorage(productDetail.getStorage());
        return productDetailDTO;
    }

    public ProductDetail toEntity(ProductDetailDTO productDetailDTO) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(productRepository.findById(productDetailDTO.getProductId()).orElse(null));
        productDetail.setIngredient(productDetailDTO.getIngredient());
        productDetail.setUsage(productDetailDTO.getUsage());
        productDetail.setUsageInstructions(productDetailDTO.getUsageInstructions());
        productDetail.setDescription(productDetailDTO.getDescription());
        productDetail.setSideEffects(productDetailDTO.getSideEffects());
        productDetail.setPrecautions(productDetailDTO.getPrecautions());
        productDetail.setStorage(productDetailDTO.getStorage());
        return productDetail;
    }
}
