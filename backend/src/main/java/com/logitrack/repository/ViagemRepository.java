package com.logitrack.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.logitrack.model.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Integer> {

	// Traz o veículo junto (JOIN) para evitar uma query extra por viagem (N+1)
	@Override
	@EntityGraph(attributePaths = "veiculo")
	List<Viagem> findAll();

	// idIgnorado: na edição, exclui a própria viagem da verificação (na criação é null).
	@Query("""
			SELECT COUNT(v) > 0 FROM Viagem v
			WHERE v.veiculo.id = :veiculoId
			  AND v.dataSaida < :chegada
			  AND v.dataChegada > :saida
			  AND (:idIgnorado IS NULL OR v.id <> :idIgnorado)
			""")
	boolean existeSobreposicao(
			@Param("veiculoId") Integer veiculoId,
			@Param("saida") LocalDateTime saida,
			@Param("chegada") LocalDateTime chegada,
			@Param("idIgnorado") Integer idIgnorado);
}
