package com.workintech.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", schema = "public")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "img", nullable = false)
    private String img;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "gender", nullable = false)
    private String gender;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    public Category(Long id, String code, String title, String img, Double rating, String gender) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.img = img;
        this.rating = rating;
        this.gender = gender;
    }
}
