package com.example.ecommercebe.controller;

import com.example.ecommercebe.dto.CategoryDTO;
import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.repositories.CategoryRepository;
import com.example.ecommercebe.service.CategoryService;
import com.example.ecommercebe.service.ProductService;
import com.example.ecommercebe.entities.Product;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.ecommercebe.dto.ProductDTO;
import com.example.ecommercebe.service.ProductService;
import com.example.ecommercebe.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Product", description = "Product Controller")
@CrossOrigin()
@Valid
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping()
    public Page<ProductDTO> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(PageRequest.of(page -1, size));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getProductByName(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int limit,
                                                       @RequestParam String name) {
        Page<ProductDTO> product = productService.getProductByName(PageRequest.of(page-1,limit), name);
        if (product == null) {
            throw new NotFoundException("Product not found with id: " + name);
        }
        return ResponseEntity.ok(product);
    }
    @GetMapping("Categories/{categoryId}")
    public Page<ProductDTO> findByCategory( @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int limit,
                                            @PathVariable Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(!category.isPresent()){
            throw new RuntimeException("Can not find category with id " + categoryId);
        }
        return productService.findByCategory(PageRequest.of(page-1, limit),category.get());
    }
    @PostMapping()
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        productService.addProduct(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO updatedProductDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(id, updatedProductDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.moveToTrash(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> handleProductNotFoundException(NotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    @GetMapping("/trash")
    public ResponseEntity<?> getInTrashCategory(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "limit", defaultValue = "10") int limit){
        return ResponseEntity.ok(productService.getInTrash(PageRequest.of(page -1, limit)));
    }
}
