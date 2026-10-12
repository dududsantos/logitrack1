package com.logitrack.mapper;

import com.logitrack.dto.request.ViagemRequest;
import com.logitrack.dto.response.ViagemResponse;
import com.logitrack.model.Veiculo;
import com.logitrack.model.Viagem;

public final class ViagemMapper {

	private ViagemMapper() {
	}

	public static ViagemResponse toResponse(Viagem viagem) {
		return new ViagemResponse(
				viagem.getId(),
				VeiculoMapper.toResponse(viagem.getVeiculo()),
				viagem.getDataSaida(),
				viagem.getDataChegada(),
				viagem.getOrigem(),
				viagem.getDestino(),
				viagem.getKmPercorrida());
	}

	// Usado tanto na criação e edicao
	public static void preencher(Viagem viagem, ViagemRequest request, Veiculo veiculo) {
		viagem.setVeiculo(veiculo);
		viagem.setDataSaida(request.dataSaida());
		viagem.setDataChegada(request.dataChegada());
		viagem.setOrigem(request.origem());
		viagem.setDestino(request.destino());
		viagem.setKmPercorrida(request.kmPercorrida());
	}
}
