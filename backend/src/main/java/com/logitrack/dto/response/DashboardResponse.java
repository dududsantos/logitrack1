package com.logitrack.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.logitrack.dto.projection.CronogramaManutencaoProjection;
import com.logitrack.dto.projection.RankingVeiculoProjection;
import com.logitrack.dto.projection.VolumeCategoriaProjection;

public record DashboardResponse(
		BigDecimal totalKm,
		List<VolumeCategoriaProjection> volumePorCategoria,
		List<CronogramaManutencaoProjection> proximasManutencoes,
		List<RankingVeiculoProjection> rankingUtilizacao,
		BigDecimal projecaoFinanceiraMes) {
}
