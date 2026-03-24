package com.jwtAuthenticationApp.mapper;

import com.jwtAuthenticationApp.dto.RegisterRequest;
import com.jwtAuthenticationApp.dto.RegisterResponseDto;
import com.jwtAuthenticationApp.dto.UserResponseDto;
import com.jwtAuthenticationApp.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-24T18:44:28+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUserEntity(RegisterRequest requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( requestDto.getUsername() );
        user.email( requestDto.getEmail() );
        user.password( requestDto.getPassword() );
        user.role( requestDto.getRole() );

        return user.build();
    }

    @Override
    public RegisterResponseDto toRegisterResponseDto(User request) {
        if ( request == null ) {
            return null;
        }

        RegisterResponseDto registerResponseDto = new RegisterResponseDto();

        registerResponseDto.setId( request.getId() );
        registerResponseDto.setUsername( request.getUsername() );
        registerResponseDto.setEmail( request.getEmail() );

        return registerResponseDto;
    }

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setUsername( user.getUsername() );
        userResponseDto.setRole( user.getRole() );

        return userResponseDto;
    }
}
