package com.nek.api.dto;

import java.time.LocalDateTime;

import com.nek.api.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "User", description = "Usuario")
public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getActive(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
