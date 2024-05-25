package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDTO> getAllCategory();
    CategoryDTO getCategoryById(Integer id);
    void addCategory(CategoryDTO categoryDTO);
    void updateCategory(Integer id, CategoryDTO categoryDTO);
    void deleteCategory(Integer id);
    Category convertToEntityId(Integer id, CategoryDTO categoryDTO);
    List<CategoryDTO> getCategoryByName(String name);
    List<CategoryDTO> getCategoryByParent(Category parent);
    List<CategoryDTO> getCategoryByParentIsNull();
}
