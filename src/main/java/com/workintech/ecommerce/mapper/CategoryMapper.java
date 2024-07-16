package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.CategoryDTO;
import com.workintech.ecommerce.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getCode(),
                category.getTitle(),
                category.getImg(),
                category.getRating(),
                category.getGender()
        );
    }

    public Category toEntity(CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.getId(),
                categoryDTO.getCode(),
                categoryDTO.getTitle(),
                categoryDTO.getImg(),
                categoryDTO.getRating(),
                categoryDTO.getGender()
        );
    }
}
