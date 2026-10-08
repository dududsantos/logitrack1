package com.logitrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.logitrack.model.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Integer> {

	// Traz o veículo junto (JOIN) para evitar uma query extra por viagem (N+1)
	@Override
	@EntityGraph(attributePaths = "veiculo")
	List<Viagem> findAll();
}
