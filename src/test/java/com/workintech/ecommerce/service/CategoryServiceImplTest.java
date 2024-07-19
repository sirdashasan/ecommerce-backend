package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.CategoryDTO;
import com.workintech.ecommerce.entity.Category;

import com.workintech.ecommerce.exceptions.ApiException;
import com.workintech.ecommerce.mapper.CategoryMapper;
import com.workintech.ecommerce.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository,categoryMapper);
        category = new Category(1L, "code1", "Title1", "img1", 4.5, "k");
        categoryDTO = new CategoryDTO(1L, "code1", "Title1", "img1", 4.5, "k");
    }

    @Test
    void getAllCategories() {
        categoryService.getAllCategories();
        verify(categoryRepository).findAll();
    }


    @Test
    void createCategory() {

        given(categoryMapper.toEntity(any(CategoryDTO.class))).willReturn(category);
        given(categoryRepository.save(any(Category.class))).willReturn(category);
        given(categoryMapper.toDTO(any(Category.class))).willReturn(categoryDTO);


        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);


        assertNotNull(createdCategory);
        assertEquals("Title1", createdCategory.getTitle());

        verify(categoryMapper).toEntity(any(CategoryDTO.class));
        verify(categoryRepository).save(any(Category.class));
        verify(categoryMapper).toDTO(any(Category.class));

    }

    @Test
    void canNotCreateCategory() {

        given(categoryRepository.existsById(categoryDTO.getId())).willReturn(true);


        assertThatThrownBy(() -> categoryService.createCategory(categoryDTO))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("Category already exists with id: " + categoryDTO.getId());


        verify(categoryRepository).existsById(categoryDTO.getId());
    }




}