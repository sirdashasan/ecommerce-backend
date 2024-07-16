package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.ProductDTO;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getStoreId(),
                product.getCategory().getId(),
                product.getRating(),
                product.getSellCount(),
                product.getImageUrls()
        );
    }

    public Product toEntity(ProductDTO productDTO, Category category) {
        return new Product(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getStock(),
                productDTO.getStoreId(),
                category,
                productDTO.getRating(),
                productDTO.getSellCount(),
                productDTO.getImages()
        );
    }
}
