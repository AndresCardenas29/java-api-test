package com.nek.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/** Datos para crear o actualizar un usuario. */
@Schema(name = "UserRequest", description = "Datos para crear o actualizar un usuario")
public record UserRequestDTO(
        @Schema(example = "Ana")
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
        String firstName,

        @Schema(example = "Gómez")
        @NotBlank(message = "El apellido es obligatorio")
        @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
        String lastName,

        @Schema(example = "ana.gomez@example.com")
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe ser válido")
        @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
        String email,

        @Schema(example = "+573001234567")
        @Size(max = 15, message = "El teléfono no puede tener más de 15 caracteres")
        @Pattern(regexp = "^\\+?[0-9 ]*$", message = "El teléfono solo puede tener números, espacios y un + inicial")
        String phone) {
}
