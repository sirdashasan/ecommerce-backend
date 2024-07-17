package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.ProductDTO;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.exceptions.ApiException;
import com.workintech.ecommerce.mapper.ProductMapper;
import com.workintech.ecommerce.repository.CategoryRepository;
import com.workintech.ecommerce.repository.OrderRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final OrderRepository orderRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, OrderRepository orderRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.orderRepository = orderRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        return productMapper.toDTO(product);
    }

    @Transactional
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productDTO.getId() != null && productRepository.existsById(productDTO.getId())) {
            throw new ApiException("Product already exists with id: " + productDTO.getId(), HttpStatus.BAD_REQUEST);
        }
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ApiException("Category not found with id: " + productDTO.getCategoryId(), HttpStatus.NOT_FOUND));

        List<Order> orders = productDTO.getOrderIds() != null ?
                orderRepository.findAllById(productDTO.getOrderIds()) : Collections.emptyList(); // null kontrolü ekleyin
        Product product = productMapper.toEntity(productDTO, category, orders);
        return productMapper.toDTO(productRepository.save(product));
    }

    @Transactional
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException("Product not found with id: " + id, HttpStatus.NOT_FOUND));
        Category category = categoryRepository.findById(productDetails.getCategoryId())
                .orElseThrow(() -> new ApiException("Category not found with id: " + productDetails.getCategoryId(), HttpStatus.NOT_FOUND));

        List<Order> orders = orderRepository.findAllById(productDetails.getOrderIds());
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setStoreId(productDetails.getStoreId());
        product.setCategory(category);
        product.setRating(productDetails.getRating());
        product.setSellCount(productDetails.getSellCount());
        product.setImageUrls(productDetails.getImages());
        product.setOrders(orders);

        return productMapper.toDTO(productRepository.save(product));
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ApiException("Product not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
}
