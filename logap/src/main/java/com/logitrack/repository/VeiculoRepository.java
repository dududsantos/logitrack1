package com.logitrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logitrack.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
}
