package com.example.ecommercebe.service;


import com.example.ecommercebe.entities.Category;
import com.example.ecommercebe.dto.CategoryDTO;
import com.example.ecommercebe.exception.CategoryNotFoundException;
import com.example.ecommercebe.mapper.CategoryMapper;
import com.example.ecommercebe.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryDTO> getAllCategory(Pageable pageable) {
        return categoryRepository.findByDeletedAtIsNull(pageable).map(CategoryMapper::toDTO);
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new RuntimeException("Can not find category with id " + id);
        }
        return CategoryMapper.toDTO(category.get());
    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParent_id() != null) {
            Category parent = categoryRepository.findById(categoryDTO.getParent_id())
                    .orElseThrow(() -> new CategoryNotFoundException("Parent category not found with id: " + categoryDTO.getParent_id()));

            if(parent.getParent() != null && parent.getParent().getParent()!=null)
                throw new RuntimeException("Can not create category with parent id is "+ categoryDTO.getParent_id());

            category.setParent(parent);
        }
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Integer id, CategoryDTO updatedCategoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        category.setName(updatedCategoryDTO.getName());
        if (updatedCategoryDTO.getParent_id() != null) {
            Category parent = categoryRepository.findById(updatedCategoryDTO.getParent_id())
                    .orElseThrow(() -> new CategoryNotFoundException("Parent category not found with id: " + updatedCategoryDTO.getParent_id()));
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

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
        return categoryRepository.findByParent(parent).stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    public Page<CategoryDTO> getCategoryByParentIsNull(Pageable pageable){
        Page<Category> category = categoryRepository.findByParentIsNullAndDeletedAtIsNull(pageable);
        return category.map(CategoryMapper:: toDTO);
    }

    public void moveToTrash(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException("Cannot find this category id: " + id);
        }
        LocalDateTime now = LocalDateTime.now();
        category.setDeletedAt(now);
        categoryRepository.save(category);
    }

    @Override
    public Page<CategoryDTO> getInTrash(Pageable pageable) {
        Page<Category> categories = categoryRepository.findByDeletedAtIsNotNull(pageable);
        return categories.map(CategoryMapper::toDTO);
    }

}