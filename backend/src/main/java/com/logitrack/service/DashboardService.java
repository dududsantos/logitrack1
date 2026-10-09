package com.logitrack.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logitrack.dto.response.DashboardResponse;
import com.logitrack.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

	private final DashboardRepository dashboardRepository;

	
	@Transactional(readOnly = true)
	public DashboardResponse gerar(Integer veiculoId) {
		return new DashboardResponse(
				dashboardRepository.totalKm(veiculoId),
				dashboardRepository.volumePorCategoria(),
				dashboardRepository.proximasManutencoes(),
				dashboardRepository.rankingUtilizacao(),
				dashboardRepository.projecaoFinanceiraMes());
	}
}
