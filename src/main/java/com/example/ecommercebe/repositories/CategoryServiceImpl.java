package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        List<CategoryDTO> dto = new ArrayList<CategoryDTO>();
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParent_id() != null) {
            Category parent = categoryRepository.findById(categoryDTO.getParent_id())
                    .orElseThrow(() -> new CategoryNotFoundException("Parent category not found with id: " + categoryDTO.getParent_id()));
            category.setParent(parent);
        }
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Integer id, CategoryDTO updatedCategoryDTO) {
        Category category = convertToEntityId(id, updatedCategoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParent_id() != null) {
            Category parent = categoryRepository.findById(categoryDTO.getParent_id()).orElse(null);
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

        return category;
    }

    @Override
    public Category convertToEntityId(Integer id, CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParent_id() != null) {
            Category parent = categoryRepository.findById(categoryDTO.getParent_id()).orElse(null);
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

        return category;
    }

    @Override
    public List<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> getCategoryByParent(Category parent) {
        return categoryRepository.findByParent(parent);
    }

    public List<Category> getCategoryByParentIsNull(){
        return categoryRepository.findByParentIsNull();
    }

    public List<Category> getCategoryByParentIsNotNull(){
        return categoryRepository.findByParentIsNotNull();
    }
}
