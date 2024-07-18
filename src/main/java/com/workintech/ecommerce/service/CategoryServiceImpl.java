package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CategoryDTO;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.exceptions.ApiException;
import com.workintech.ecommerce.mapper.CategoryMapper;
import com.workintech.ecommerce.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException("Category not found with id: " + id, HttpStatus.NOT_FOUND));
        return categoryMapper.toDTO(category);
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getId() != null && categoryRepository.existsById(categoryDTO.getId())) {
            throw new ApiException("Category already exists with id: " + categoryDTO.getId(), HttpStatus.BAD_REQUEST);
        }
        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }


    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException("Category not found with id: " + id, HttpStatus.NOT_FOUND));
        category.setCode(categoryDetails.getCode());
        category.setTitle(categoryDetails.getTitle());
        category.setImg(categoryDetails.getImg());
        category.setRating(categoryDetails.getRating());
        category.setGender(categoryDetails.getGender());
        categoryMapper.toDTO(categoryRepository.save(category));
        throw new ApiException("Category successfully updated with id: " + id, HttpStatus.OK);
    }


    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ApiException("Category not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDTO> getCategoriesByGender(String gender) {
        return categoryRepository.findCategoriesByGender(gender).stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }
}



