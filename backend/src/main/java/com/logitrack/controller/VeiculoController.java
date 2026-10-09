package com.logitrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logitrack.dto.response.VeiculoResponse;
import com.logitrack.service.VeiculoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor 
public class VeiculoController {

	private final VeiculoService veiculoService;

	@GetMapping
	public List<VeiculoResponse> listar() {
		return veiculoService.listar();
	}
}
