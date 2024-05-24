package com.example.ecommercebe.controller;

import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.mapper.CategoryMapper;
import com.example.ecommercebe.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/allcategory")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategoryParent(){
        List<CategoryDTO> category = categoryService.getCategoryByParentIsNull();
        return ResponseEntity.ok(category);
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

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id ,@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if(result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> fieldError.getField(),fieldError -> fieldError.getDefaultMessage()));
        return  new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/children/{parentId}")
    public List<CategoryDTO> getCategoryByParent(@PathVariable Integer parentId) {
        Category parent = new Category();
        parent.setId(parentId);
        List<CategoryDTO> category = categoryService.getCategoryByParent(parent);
        if (category == null) {
            throw new CategoryNotFoundException("Parent Category not found with id: " + parentId);
        }
        return category;
    }

}
