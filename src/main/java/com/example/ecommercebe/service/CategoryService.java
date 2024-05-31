package com.example.ecommercebe.service;

import com.example.ecommercebe.dto.UserDTO;
import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Page<CategoryDTO> getAllCategory(Pageable pageable);
    CategoryDTO getCategoryById(Integer id);
    void addCategory(CategoryDTO categoryDTO);
    void updateCategory(Integer id, CategoryDTO categoryDTO);
    void deleteCategory(Integer id);
    Category convertToEntityId(Integer id, CategoryDTO categoryDTO);
    List<CategoryDTO> getCategoryByName(String name);
    List<CategoryDTO> getCategoryByParent(Category parent);
    Page<CategoryDTO> getCategoryByParentIsNull(Pageable pageable);
    void moveToTrash(Integer id);
    Page<CategoryDTO> getInTrash(Pageable pageable);
}
