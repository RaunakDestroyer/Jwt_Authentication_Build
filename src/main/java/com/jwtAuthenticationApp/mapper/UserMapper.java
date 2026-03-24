package com.jwtAuthenticationApp.mapper;

import org.mapstruct.Mapper;

import com.jwtAuthenticationApp.dto.RegisterRequest;
import com.jwtAuthenticationApp.dto.RegisterResponseDto;
import com.jwtAuthenticationApp.dto.UserResponseDto;
import com.jwtAuthenticationApp.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public User toUserEntity(RegisterRequest requestDto);
	
	public RegisterResponseDto toRegisterResponseDto(User request);
	
	public UserResponseDto toUserResponseDto(User user);
}
