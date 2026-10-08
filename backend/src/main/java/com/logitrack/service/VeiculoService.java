package com.logitrack.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logitrack.dto.response.VeiculoResponse;
import com.logitrack.mapper.VeiculoMapper;
import com.logitrack.repository.VeiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeiculoService {

	private final VeiculoRepository veiculoRepository;

	@Transactional(readOnly = true)
	public List<VeiculoResponse> listar() {
		return veiculoRepository.findAll().stream()
				.map(VeiculoMapper::toResponse)
				.toList();
	}
}
