package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.OrderDTO;
import com.workintech.ecommerce.dto.ProductDTO;
import com.workintech.ecommerce.entity.Category;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.Product;
import com.workintech.ecommerce.entity.User;
import com.workintech.ecommerce.exceptions.ApiException;
import com.workintech.ecommerce.mapper.OrderMapper;
import com.workintech.ecommerce.repository.OrderRepository;
import com.workintech.ecommerce.repository.ProductRepository;
import com.workintech.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        if (orderDTO.getUserId() == null) {
            throw new ApiException("User ID is required", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ApiException("User not found with id: " + orderDTO.getUserId(), HttpStatus.NOT_FOUND));

        List<Product> products = orderDTO.getProductIds() != null ?
                productRepository.findAllById(orderDTO.getProductIds()) : Collections.emptyList();

        if (orderDTO.getProductIds() != null && products.size() != orderDTO.getProductIds().size()) {
            throw new ApiException("Some products not found", HttpStatus.NOT_FOUND);
        }

        Order order = orderMapper.toEntity(orderDTO, user, products);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDetails) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        User user = userRepository.findById(orderDetails.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Product> products = productRepository.findAllById(orderDetails.getProductIds());

        if (products.size() != orderDetails.getProductIds().size()) {
            throw new RuntimeException("Some products not found");
        }

        existingOrder.setUser(user);
        existingOrder.setOrderDate(orderDetails.getOrderDate());
        existingOrder.setPrice(orderDetails.getPrice());
        existingOrder.setProducts(products);

        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDTO(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(existingOrder);
    }
}
