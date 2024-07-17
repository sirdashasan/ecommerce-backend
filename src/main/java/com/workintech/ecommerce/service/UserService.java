package com.workintech.ecommerce.service;

import com.workintech.ecommerce.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDetails);
    void deleteUser(Long id);
}
