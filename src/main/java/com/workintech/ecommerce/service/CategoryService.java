package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDetails);
    void deleteCategory(Long id);
    List<CategoryDTO> getCategoriesByGender(String gender);
}
