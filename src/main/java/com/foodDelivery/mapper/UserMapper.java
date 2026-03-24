package com.foodDelivery.mapper;

import org.mapstruct.Mapper;

import com.foodDelivery.dto.RegisterRequest;
import com.foodDelivery.dto.RegisterResponseDto;
import com.foodDelivery.dto.UserResponseDto;
import com.foodDelivery.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public User toUserEntity(RegisterRequest requestDto);
	
	public RegisterResponseDto toRegisterResponseDto(User request);
	
	public UserResponseDto toUserResponseDto(User user);
}
