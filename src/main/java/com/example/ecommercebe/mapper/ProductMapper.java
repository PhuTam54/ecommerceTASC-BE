package com.example.ecommercebe.mapper;

import com.example.ecommercebe.dto.CategoryDTO;
import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setDetail(product.getDetail());
        productDTO.setManufacturer(product.getManufacturer());
        productDTO.setCategoryId((product.getCategory().getId()));
        return productDTO;
    }

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setDetail(productDTO.getDetail());
        product.setManufacturer(productDTO.getManufacturer());
        product.setCategory(categoryRepository.findById((int) productDTO.getCategoryId()).orElse(null));
        return product;
    }}
