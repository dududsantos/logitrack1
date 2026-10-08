package com.logitrack.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ViagemRequest(
		
		@NotNull Integer veiculoId,
		@NotNull LocalDateTime dataSaida,
		@NotNull LocalDateTime dataChegada,
		@NotBlank String origem,
		@NotBlank String destino,
		@NotNull @Positive BigDecimal kmPercorrida) {
}
