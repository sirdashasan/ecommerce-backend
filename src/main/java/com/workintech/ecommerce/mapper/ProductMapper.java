package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.ProductDTO;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Product;

import org.springframework.stereotype.Component;
import com.workintech.ecommerce.entity.Order;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


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
                product.getImageUrls(),
                product.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList())
        );
    }

    public Product toEntity(ProductDTO productDTO, Category category, List<Order> orders) {
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
                productDTO.getImages(),
                orders != null ? orders : Collections.emptyList()
        );
    }
}
