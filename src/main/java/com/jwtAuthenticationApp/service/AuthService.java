package com.jwtAuthenticationApp.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtAuthenticationApp.dto.LoginRequest;
import com.jwtAuthenticationApp.dto.LoginResponse;
import com.jwtAuthenticationApp.dto.RegisterRequest;
import com.jwtAuthenticationApp.dto.RegisterResponseDto;
import com.jwtAuthenticationApp.dto.UserResponseDto;
import com.jwtAuthenticationApp.entity.User;
import com.jwtAuthenticationApp.mapper.UserMapper;
import com.jwtAuthenticationApp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository repo;
	private final PasswordEncoder encoder;
	private final AuthenticationManager manager;
	private final JwtService jwt;
	private final UserMapper mapper;

	public RegisterResponseDto register(RegisterRequest request) {

		if (repo.findByUsername(request.getUsername()).isPresent()) {
			throw new IllegalArgumentException("Username already exists");
		}

		 User user = User.builder()
		            .username(request.getUsername())
		            .password(encoder.encode(request.getPassword()))
		            .email(request.getEmail())
		            .role(request.getRole())
		            .build();

		    repo.save(user);

		    RegisterResponseDto response = mapper.toRegisterResponseDto(user);
		    response.setMessage("User created successfully!");

		    return response;
	}

	public LoginResponse login(LoginRequest req) {

		manager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

		User user = repo.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

		String token = jwt.generateToken(user);

		return new LoginResponse(token, user.getUsername(), user.getRole(), "Login successful");
	}

	public UserResponseDto validate(String token) {

		if (!jwt.validateToken(token)) {
			throw new RuntimeException("Invalid or expired token"); // means invalid
		}

		String username = jwt.extractUsername(token);
		User user = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

		return mapper.toUserResponseDto(user);
	}

}