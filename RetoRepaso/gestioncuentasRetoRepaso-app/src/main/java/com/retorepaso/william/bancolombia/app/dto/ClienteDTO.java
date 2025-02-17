package com.retorepaso.william.bancolombia.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteDTO(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @Email(message = "Debe proporcionar un email válido")
        @NotBlank(message = "El email es obligatorio")
        String email,

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(min = 10, max = 15, message = "El teléfono debe tener entre 10 y 15 caracteres")
        String telefono,

        @NotBlank(message = "La dirección es obligatoria")
        String direccion
) {}
