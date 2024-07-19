package com.workintech.ecommerce.repository;

import com.workintech.ecommerce.entity.Role;
import com.workintech.ecommerce.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @DisplayName("Can find user by username")
    @Test
    void findUserByUsername() {

        Optional<Role> userRole = roleRepository.findByAuthority("USER");

        User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("test123");
        user1.setAuthorities(new HashSet<>());
        user1.setAuthorities(new HashSet<>(Set.of(userRole.get())));
        userRepository.save(user1);

        Optional<User> foundUser = userRepository.findUserByUsername("test");

        assertTrue(foundUser.isPresent());
        assertEquals("test", foundUser.get().getUsername());
        assertEquals("test123", foundUser.get().getPassword());
        assertEquals(1, foundUser.get().getAuthorities().size());
        assertEquals("USER", foundUser.get().getAuthorities().iterator().next().getAuthority());
    }
}