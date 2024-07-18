package com.workintech.ecommerce.controller;

import com.workintech.ecommerce.dto.RegisterRequestDTO;
import com.workintech.ecommerce.dto.RegisterResponseDTO;
import com.workintech.ecommerce.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDto) {
        RegisterResponseDTO response = authenticationService.register(
                registerRequestDto.getUsername(),
                registerRequestDto.getPassword(),
                registerRequestDto.getRole()
        );
        return ResponseEntity.ok(response);
    }
    }
