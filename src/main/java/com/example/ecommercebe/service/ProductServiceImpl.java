package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.ProductDTO;

import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.exception.NotFoundException;
import com.example.ecommercebe.mapper.ProductMapper;
import com.example.ecommercebe.repositories.CategoryRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findByDeletedAtIsNull(pageable);
        List<ProductDTO> productDTOs = productPage.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(productDTOs, pageable, productPage.getTotalElements());
    }

    @Override
    public Page<ProductDTO> getProductByName(Pageable pageable,String name) {
       return productRepository.findByNameAndDeletedAtIsNull(pageable,name)
               .map(productMapper::toDTO);
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public Page<ProductDTO> findByCategory(Pageable pageable,Category category) {
        return productRepository.findByCategoryAndDeletedAtIsNull(pageable,category).map(productMapper::toDTO);
    }


    @Override
    public void updateProduct(long id, ProductDTO updatedProductDTO) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new RuntimeException("Can not find product with id" + id);
        }
        if(updatedProductDTO.getPrice()!= null){
            existingProduct.get().setPrice(updatedProductDTO.getPrice());
        }
        if(updatedProductDTO.getName()!= null){
            existingProduct.get().setName(updatedProductDTO.getName());
        }
        if(updatedProductDTO.getDescription()!= null){
            existingProduct.get().setDescription(updatedProductDTO.getDescription());
        }
        if(updatedProductDTO.getManufacturer()!= null){
            existingProduct.get().setManufacturer(updatedProductDTO.getManufacturer());
        }
        if(updatedProductDTO.getCategoryId()!= null){
            Optional<Category> category = categoryRepository.findById(updatedProductDTO.getCategoryId());
            if(category.isEmpty()){
                throw new RuntimeException("Can not find category with id " + updatedProductDTO.getCategoryId());
            }
            existingProduct.get().setCategory(category.get());
        }
        productRepository.save(existingProduct.get());

    }


    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
    @Override
    public void moveToTrash(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NotFoundException("Cannot find this product id: " + id);
        }
        LocalDateTime now = LocalDateTime.now();
        product.setDeletedAt(now);
        productRepository.save(product);
    }

    @Override
    public Page<ProductDTO> getInTrash(Pageable pageable) {
        Page<Product> products = productRepository.findByDeletedAtIsNotNull(pageable);
        return products.map(productMapper::toDTO);
    }

}