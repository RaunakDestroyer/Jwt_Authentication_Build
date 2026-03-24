package com.foodDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDto {
    private Long id;
    private String username;
    private String email;
    private String message; // e.g., "user created. verify email to activate."
    // getters/setters, constructors
}