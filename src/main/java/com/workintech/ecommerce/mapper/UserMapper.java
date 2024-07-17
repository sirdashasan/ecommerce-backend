package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.UserDTO;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getOrders() != null ? user.getOrders().stream().map(order ->
                        order.getId()).collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setOrders(userDTO.getOrderIds() != null ? userDTO.getOrderIds().stream().map(id -> {
            Order order = new Order();
            order.setId(id);
            return order;
        }).collect(Collectors.toList()) : Collections.emptyList());
        return user;
    }
}
