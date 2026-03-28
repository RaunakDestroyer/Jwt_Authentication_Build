package com.jwtAuthenticationBuild.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtAuthenticationBuild.DTO.LoginRequest;
import com.jwtAuthenticationBuild.DTO.LoginResponse;
import com.jwtAuthenticationBuild.DTO.RegisterRequest;
import com.jwtAuthenticationBuild.DTO.RegisterResponseDto;
import com.jwtAuthenticationBuild.DTO.UserResponseDto;
import com.jwtAuthenticationBuild.Service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService service;
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody RegisterRequest request){
		RegisterResponseDto savedUser =  service.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
	        return ResponseEntity.ok(service.login(request));
	    }

	 @GetMapping("/validate")
	 public ResponseEntity<UserResponseDto> validate(
	         @RequestHeader("Authorization") String header) {

	     String token = header.substring(7);
	     return ResponseEntity.ok(service.validate(token));
	 }

}
