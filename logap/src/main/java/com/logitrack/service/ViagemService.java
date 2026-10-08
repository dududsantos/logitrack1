package com.logitrack.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.logitrack.dto.request.ViagemRequest;
import com.logitrack.dto.response.ViagemResponse;
import com.logitrack.mapper.ViagemMapper;
import com.logitrack.model.Veiculo;
import com.logitrack.model.Viagem;
import com.logitrack.repository.VeiculoRepository;
import com.logitrack.repository.ViagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ViagemService {

	private final ViagemRepository viagemRepository;
	private final VeiculoRepository veiculoRepository;

	@Transactional(readOnly = true)
	public List<ViagemResponse> listar() {
		return viagemRepository.findAll().stream()
				.map(ViagemMapper::toResponse)
				.toList();
	}

	@Transactional(readOnly = true)
	public ViagemResponse buscarPorId(Integer id) {
		return ViagemMapper.toResponse(buscarViagem(id));
	}

	@Transactional
	public ViagemResponse criar(ViagemRequest request) {
		Veiculo veiculo = buscarVeiculo(request.veiculoId());
		Viagem viagem = new Viagem();
		ViagemMapper.preencher(viagem, request, veiculo);
		return ViagemMapper.toResponse(viagemRepository.save(viagem));
	}

	@Transactional
	public ViagemResponse atualizar(Integer id, ViagemRequest request) {
		Viagem viagem = buscarViagem(id);
		Veiculo veiculo = buscarVeiculo(request.veiculoId());
		ViagemMapper.preencher(viagem, request, veiculo);
		return ViagemMapper.toResponse(viagemRepository.save(viagem));
	}

	@Transactional
	public void excluir(Integer id) {
		viagemRepository.delete(buscarViagem(id));
	}

	private Viagem buscarViagem(Integer id) {
		return viagemRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));
	}

	private Veiculo buscarVeiculo(Integer id) {
		return veiculoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
	}
}
