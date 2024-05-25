package com.example.ecommercebe.service;

import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.mapper.CategoryMapper;
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
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> category = new ArrayList<>();
        for (Category categories: categoryRepository.findAll()) {
            category.add(CategoryMapper.toDTO(categories));
        }
        return category;
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        return CategoryMapper.toDTO(categoryRepository.findById(id).orElse(null));    }

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
    public List<CategoryDTO> getCategoryByName(String name) {
        List<CategoryDTO> category = new ArrayList<>();
        for (Category categories: categoryRepository.findByName(name)) {
            category.add(CategoryMapper.toDTO(categories));
        }
        return category;
    }

    public List<CategoryDTO> getCategoryByParent(Category parent) {
        List<Category> category = categoryRepository.findByParent(parent);

        List<CategoryDTO> category1 = new ArrayList<>();
        for (Category categories: category) {
            category1.add(CategoryMapper.toDTO(categories));
        }
        return category1;
    }

    public List<CategoryDTO> getCategoryByParentIsNull(){
        List<CategoryDTO> category = new ArrayList<>();
        for (Category categories: categoryRepository.findByParentIsNull()) {
            category.add(CategoryMapper.toDTO(categories));
        }
        return category;
    }


}
