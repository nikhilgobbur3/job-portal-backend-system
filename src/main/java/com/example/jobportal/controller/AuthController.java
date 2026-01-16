package com.example.jobportal.controller;

import com.example.jobportal.dto.ApiResponse;
import com.example.jobportal.dto.AuthRequest;
import com.example.jobportal.dto.AuthResponse;
import com.example.jobportal.dto.UserDTO;
import com.example.jobportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@Valid @RequestBody UserDTO userDTO) {
        UserDTO created = authService.register(userDTO);
        ApiResponse<UserDTO> resp = new ApiResponse<>("User registered", created, 201, null);
        return ResponseEntity.status(201).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse token = authService.login(request);
        ApiResponse<AuthResponse> resp = new ApiResponse<>("Login successful", token, 200, null);
        return ResponseEntity.ok(resp);
    }
}
