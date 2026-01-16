package com.example.jobportal.service.impl;

import com.example.jobportal.dto.AuthRequest;
import com.example.jobportal.dto.AuthResponse;
import com.example.jobportal.dto.UserDTO;
import com.example.jobportal.entity.User;
import com.example.jobportal.repository.UserRepository;
import com.example.jobportal.security.JwtTokenProvider;
import com.example.jobportal.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AuthResponse login(AuthRequest request) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        String token = tokenProvider.generateToken(auth);
        return new AuthResponse(token);
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(com.example.jobportal.entity.Role.USER);
        User saved = userRepository.save(user);
        UserDTO out = modelMapper.map(saved, UserDTO.class);
        out.setPassword(null);
        return out;
    }
}
