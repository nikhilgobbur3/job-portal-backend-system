package com.example.jobportal.service;

import com.example.jobportal.dto.AuthRequest;
import com.example.jobportal.dto.AuthResponse;
import com.example.jobportal.dto.UserDTO;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    UserDTO register(UserDTO userDTO);
}
