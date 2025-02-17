package com.retorepaso.william.bancolombia.app.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PrestamoDTO(
        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser positivo")
        Double monto,

        @NotNull(message = "La tasa de interés es obligatoria")
        @Positive(message = "La tasa de interés debe ser positiva")
        Double interes,

        @NotNull(message = "La duración es obligatoria")
        @Min(value = 1, message = "El plazo debe ser al menos 1 mes")
        Integer duracionMeses,

        @NotNull(message = "El ID del cliente es obligatorio")
        Long clienteId
) {}