package com.logitrack.dto.response;

import com.logitrack.model.TipoVeiculo;

public record VeiculoResponse(
		Integer id,
		String placa,
		String modelo,
		TipoVeiculo tipo,
		Integer ano ) {
}
