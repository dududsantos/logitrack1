package com.logitrack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logitrack.dto.response.DashboardResponse;
import com.logitrack.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

	private final DashboardService dashboardService;

	@GetMapping
	public DashboardResponse gerar(@RequestParam(required = false) Integer veiculoId) {
		return dashboardService.gerar(veiculoId);
	}
}
