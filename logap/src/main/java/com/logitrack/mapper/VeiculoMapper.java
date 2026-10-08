package com.logitrack.mapper;

import com.logitrack.dto.response.VeiculoResponse;
import com.logitrack.model.Veiculo;

public final class VeiculoMapper {

	private VeiculoMapper() {}
	

	public static VeiculoResponse toResponse(Veiculo veiculo) {
		return new VeiculoResponse(
				veiculo.getId(),
				veiculo.getPlaca(),
				veiculo.getModelo(),
				veiculo.getTipo(),
				veiculo.getAno()
			);
	}
}
