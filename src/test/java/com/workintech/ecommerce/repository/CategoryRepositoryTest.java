package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryTest(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @DisplayName("Can find category by gender")
    @Test
    void findCategoriesByGender() {
        Category category1 = new Category();
        category1.setCode("e:test");
        category1.setTitle("Test Erkek");
        category1.setImg("https://res.cloudinary.com/upwork-cloud/image/upload/c_scale,w_1000/v1709194994/catalog/1628716571515064320/grs9nrqvlcpuqonofblm.webp");
        category1.setRating(4.5);
        category1.setGender("e");

        categoryRepository.save(category1);

        List<Category> erkekKategoriler = categoryRepository.findCategoriesByGender("e");

        assertNotNull(erkekKategoriler);





    }
}