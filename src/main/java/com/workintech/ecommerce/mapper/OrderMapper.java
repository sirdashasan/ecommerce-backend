package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.entity.Product;
import org.springframework.stereotype.Component;
import com.workintech.ecommerce.dto.OrderDTO;
import com.workintech.ecommerce.entity.Order;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.workintech.ecommerce.entity.User;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getUser().getId(),
                order.getOrderDate(),
                order.getPrice(),
                order.getProducts().stream().map(product -> product.getId()).collect(Collectors.toList())
        );
    }

    public Order toEntity(OrderDTO orderDTO, User user, List<Product> products) {
        Order order = new Order();
        if (orderDTO.getId() != null) {
            order.setId(orderDTO.getId());
        }
        order.setUser(user);
        order.setOrderDate(orderDTO.getOrderDate());
        order.setPrice(orderDTO.getPrice());
        order.setProducts(products != null ? products : Collections.emptyList());
        return order;
    }


}
