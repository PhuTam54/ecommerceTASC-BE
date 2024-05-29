package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.mapper.ProductMapper;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductDTO> productDTOs = productPage.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, productPage.getTotalElements());
    }

    @Override
    public ProductDTO getProductByName(String name) {
        return
                productMapper.toDTO(productRepository.findByName(name).orElse(null));

    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public List<ProductDTO> findByCategory(Category category) {
        return productRepository.findByCategory(category).stream().map(productMapper::toDTO).toList().stream().collect(Collectors.toList());
    }


    @Override
    public void updateProduct(long id, ProductDTO updatedProductDTO) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            Product updatedProduct = productMapper.toEntity(updatedProductDTO);
            updatedProduct.setId(existingProduct.getId());
            productRepository.save(updatedProduct);
        }
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
