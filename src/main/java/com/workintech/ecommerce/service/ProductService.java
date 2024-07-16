package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDetails);
    void deleteProduct(Long id);
    List<ProductDTO> getProductsByCategoryId(Long categoryId);
}
