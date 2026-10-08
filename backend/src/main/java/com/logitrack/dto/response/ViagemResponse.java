package com.logitrack.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record ViagemResponse(
		Integer id,
		VeiculoResponse veiculo,
		LocalDateTime dataSaida,
		LocalDateTime dataChegada,
		String origem,
		String destino,
		BigDecimal kmPercorrida ) {
}
