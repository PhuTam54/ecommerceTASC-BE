package com.example.ecommercebe.mapper;


import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;

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
        product.setCategory(categoryRepository.findById((productDTO.getCategoryId())).orElse(null));
        return product;
    }}
