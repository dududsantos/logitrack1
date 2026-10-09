package com.logitrack.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.logitrack.dto.projection.CronogramaManutencaoProjection;
import com.logitrack.dto.projection.RankingVeiculoProjection;
import com.logitrack.dto.projection.VolumeCategoriaProjection;
import com.logitrack.model.Viagem;

// Repository 
public interface DashboardRepository extends Repository<Viagem, Integer> {

	// 1. Total de KM da frota ou de um veículo
	@Query(value = """
			SELECT COALESCE(SUM(km_percorrida), 0)
			FROM viagens
			WHERE CAST(:veiculoId AS INTEGER) IS NULL OR veiculo_id = :veiculoId
			""", nativeQuery = true)
	BigDecimal totalKm(@Param("veiculoId") Integer veiculoId);

	
	// 2. Viagens já realizadas por categoria de veículo
	@Query(value = """
			SELECT ve.tipo AS tipo, COUNT(vi.id) AS total
			FROM veiculos ve
			LEFT JOIN viagens vi ON vi.veiculo_id = ve.id AND vi.data_chegada <= NOW()
			GROUP BY ve.tipo
			ORDER BY ve.tipo
			""", nativeQuery = true)
	List<VolumeCategoriaProjection> volumePorCategoria();

	
	// 3. Próximas 5 manutenções agendadas
	@Query(value = """
			SELECT m.id,
			       ve.placa,
			       ve.modelo,
			       m.tipo_servico     AS "tipoServico",
			       m.data_inicio      AS "dataInicio",
			       m.data_finalizacao AS "dataFinalizacao",
			       m.custo_estimado   AS "custoEstimado",
			       m.status
			FROM manutencoes m
			JOIN veiculos ve ON ve.id = m.veiculo_id
			WHERE m.data_inicio >= CURRENT_DATE
			  AND m.status <> 'CONCLUIDA'
			ORDER BY m.data_inicio
			LIMIT 5
			""", nativeQuery = true)
	List<CronogramaManutencaoProjection> proximasManutencoes();

	
	// 4. Ranking dos veículos que mais rodaram (top 5)
	@Query(value = """
			SELECT ve.id, ve.placa, ve.modelo, ve.tipo,
			       SUM(vi.km_percorrida) AS "totalKm"
			FROM viagens vi
			JOIN veiculos ve ON ve.id = vi.veiculo_id
			GROUP BY ve.id
			ORDER BY "totalKm" DESC
			LIMIT 5
			""", nativeQuery = true)
	List<RankingVeiculoProjection> rankingUtilizacao();

	
	// 5. Custo estimado das manutenções do mês atual
	@Query(value = """
			SELECT COALESCE(SUM(custo_estimado), 0)
			FROM manutencoes
			WHERE data_inicio >= date_trunc('month', CURRENT_DATE)
			  AND data_inicio <  date_trunc('month', CURRENT_DATE) + INTERVAL '1 month'
			""", nativeQuery = true)
	BigDecimal projecaoFinanceiraMes();
}
