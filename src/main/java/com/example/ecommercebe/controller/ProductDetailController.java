package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.dto.ProductDetailDTO;
import com.example.ecommercebe.entities.Product;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.mapper.ProductMapper;
import com.example.ecommercebe.repositories.ProductDetailRepository;
import com.example.ecommercebe.repositories.ProductRepository;
import com.example.ecommercebe.service.ProductDetailService;
import com.example.ecommercebe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/productDetail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductDetailById(@PathVariable long id) {
        ProductDetailDTO productDetail = productDetailService.getProductDetailById(id);
        if(productDetail == null){
            return new ResponseEntity<>("Product not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(productDetail);
    }

    public ProductDTO getProductById(long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product != null ? productMapper.toDTO(product) : null;
    }

    @PostMapping("/productDetails")
    public ResponseEntity<?> addProductDetail(@RequestBody ProductDetailDTO productDetailDTO, BindingResult result) {
        if(result.hasErrors()){
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        productDetailService.addProductDetail(productDetailDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);

        }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductDetail(@PathVariable long id, @RequestBody ProductDetailDTO updatedProductDetailDTO, BindingResult result) {
        if(result.hasErrors()){
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        productDetailService.updateProductDetail(id, updatedProductDetailDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductDetail(@PathVariable long id) {
        productDetailService.deleteProductDetail(id);
        return ResponseEntity.ok("Product Detail deleted successfully");
    }
}
