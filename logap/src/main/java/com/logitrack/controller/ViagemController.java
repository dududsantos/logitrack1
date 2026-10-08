package com.logitrack.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.logitrack.dto.request.ViagemRequest;
import com.logitrack.dto.response.ViagemResponse;
import com.logitrack.service.ViagemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/viagens")
@RequiredArgsConstructor
public class ViagemController {

	private final ViagemService viagemService;

	@GetMapping
	public List<ViagemResponse> listar() {
		return viagemService.listar();
	}

	@GetMapping("/{id}")
	public ViagemResponse buscarPorId(@PathVariable Integer id) {
		return viagemService.buscarPorId(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ViagemResponse criar(@RequestBody @Valid ViagemRequest request) {
		return viagemService.criar(request);
	}

	@PutMapping("/{id}")
	public ViagemResponse atualizar(@PathVariable Integer id, @RequestBody @Valid ViagemRequest request) {
		return viagemService.atualizar(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Integer id) {
		viagemService.excluir(id);
	}
}
