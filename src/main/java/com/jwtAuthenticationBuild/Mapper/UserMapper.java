package com.jwtAuthenticationBuild.Mapper;

import org.mapstruct.Mapper;

import com.jwtAuthenticationBuild.DTO.RegisterRequest;
import com.jwtAuthenticationBuild.DTO.RegisterResponseDto;
import com.jwtAuthenticationBuild.DTO.UserResponseDto;
import com.jwtAuthenticationBuild.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public User toUserEntity(RegisterRequest requestDto);
	
	public RegisterResponseDto toRegisterResponseDto(User request);
	
	public UserResponseDto toUserResponseDto(User user);
}
