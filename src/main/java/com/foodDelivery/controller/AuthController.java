package com.foodDelivery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodDelivery.dto.LoginRequest;
import com.foodDelivery.dto.LoginResponse;
import com.foodDelivery.dto.RegisterRequest;
import com.foodDelivery.dto.RegisterResponseDto;
import com.foodDelivery.dto.UserResponseDto;
import com.foodDelivery.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService service;
	
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
