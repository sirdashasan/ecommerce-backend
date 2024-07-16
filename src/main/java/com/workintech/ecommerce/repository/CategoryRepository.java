package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {



    @Query("SELECT c FROM Category c WHERE c.gender = :gender")
    List<Category> findCategoriesByGender(String gender);
}
