package com.example.ecommercebe.controller;

import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.exception.ProductNotFoundException;
import com.example.ecommercebe.mapper.CategoryMapper;
import com.example.ecommercebe.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @CrossOrigin(origins = "*",maxAge = 3600)
    @GetMapping("/allcategory")
    public List<CategoryDTO> getAllCategory1(){
        List<CategoryDTO> category = new ArrayList<>();
        for (Category categories: categoryService.getAllCategory()) {
            category.add(CategoryMapper.toDTO(categories));
        }
        return category;
    }


    @CrossOrigin(origins = "*",maxAge = 3600)
    @GetMapping("/")
    public List<CategoryDTO> getAllCategory(){
        List<CategoryDTO> category = new ArrayList<>();
        for (Category categories: categoryService.getCategoryByParentIsNull()) {
            category.add(CategoryMapper.toDTO(categories));
        }
        return category;
    }
    @CrossOrigin(origins = "*",maxAge = 3600)
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
            Category category = categoryService.getCategoryById(id);
            if (category == null) {
                throw new ProductNotFoundException("Category not found with id: " + id);
            }
            return ResponseEntity.ok(CategoryMapper.toDTO(category));
        }

    @GetMapping("/name/{name}")
    public List<CategoryDTO> getCategoryByName(@PathVariable String name) {
        List<CategoryDTO> category = new ArrayList<>();
        for (Category categories: categoryService.getCategoryByName(name)) {
            category.add(CategoryMapper.toDTO(categories));
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public Map<String, String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
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

    @GetMapping("/children/{parentId}")
    public List<CategoryDTO> getCategoryByParent(@PathVariable Integer parentId) {
        Category parent = new Category();
        parent.setId(parentId);
        List<Category> category = categoryService.getCategoryByParent(parent);

        List<CategoryDTO> category1 = new ArrayList<>();
        for (Category categories: category) {
            category1.add(CategoryMapper.toDTO(categories));
        }
        return category1;
    }

}
