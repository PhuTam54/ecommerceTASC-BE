package com.example.ecommercebe.controller;

import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.mapper.CategoryMapper;
import com.example.ecommercebe.repositories.CategoryRepository;
import com.example.ecommercebe.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "Category", description = "Category Controller")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/allcategory")
    public ResponseEntity<Page<CategoryDTO>> getAllCategory(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit){
        Page<CategoryDTO> categoryDTOS = categoryService.getAllCategory(PageRequest.of(page -1, limit));
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategoryParent(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit){
        return ResponseEntity.ok(categoryService.getCategoryByParentIsNull(PageRequest.of(page -1, limit)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
            CategoryDTO category = categoryService.getCategoryById(id);
            if (category == null) {
                throw new CategoryNotFoundException("Category not found with id: " + id);
            }
            return ResponseEntity.ok(category);
        }

    @GetMapping("/name/{name}")
    public List<CategoryDTO> getCategoryByName(@PathVariable String name) {
        List<CategoryDTO> category = categoryService.getCategoryByName(name);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found with name: " + name);
        }
        return category;
    }

    @PostMapping("/")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id ,@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(),fieldError -> fieldError.getDefaultMessage()));
        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.moveToTrash(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/children/{Id}")
    public List<CategoryDTO> getCategoryByParent(@PathVariable Integer Id) {
        Category parent = categoryRepository.findById(Id).orElse(null);
        if(parent == null){
            throw new RuntimeException("Parent Category not found with id:" + Id);
        }
        List<CategoryDTO> category = categoryService.getCategoryByParent(parent);
        if (category == null) {
            throw new CategoryNotFoundException("Not Found Children Category  with parent id: " + Id);
        }
        return category;
    }

    @GetMapping("/trash")
    public ResponseEntity<?> getInTrashCategory(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit){
        return ResponseEntity.ok(categoryService.getInTrash(PageRequest.of(page -1, limit)));
    }
}
