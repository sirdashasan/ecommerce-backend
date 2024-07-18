package com.workintech.ecommerce.mapper;

import com.workintech.ecommerce.dto.UserDTO;
import com.workintech.ecommerce.entity.Order;
import com.workintech.ecommerce.entity.Role;
import com.workintech.ecommerce.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getAuthorities() != null ? user.getAuthorities().stream().map(Role::getAuthority).collect(Collectors.toSet()) : Collections.emptySet(),
                user.getOrders() != null ? user.getOrders().stream().map(order -> order.getId()).collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setAuthorities(userDTO.getRoles() != null ? userDTO.getRoles().stream().map(authority -> {
            Role role = new Role();
            role.setAuthority(authority);
            return role;
        }).collect(Collectors.toSet()) : new HashSet<>());
        user.setOrders(userDTO.getOrderIds() != null ? userDTO.getOrderIds().stream().map(id -> {
            Order order = new Order();
            order.setId(id);
            return order;
        }).collect(Collectors.toList()) : Collections.emptyList());
        return user;
    }
}
