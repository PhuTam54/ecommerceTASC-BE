package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(Integer id);
    void addCategory(CategoryDTO categoryDTO);
    void updateCategory(Integer id, CategoryDTO categoryDTO);
    void deleteCategory(Integer id);
    Category convertToEntity(CategoryDTO categoryDTO);
    Category convertToEntityId(Integer id, CategoryDTO categoryDTO);
    List<Category> getCategoryByName(String name);
    List<Category> getCategoryByParent(Category parent);
    List<Category> getCategoryByParentIsNull();
    List<Category> getCategoryByParentIsNotNull();
}
