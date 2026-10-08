package com.logitrack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
public class Veiculo  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //TODO: verificar a logica diso
	private Integer id;

	private String placa;

	private String modelo;

	@Enumerated(EnumType.STRING)
	private TipoVeiculo tipo;

	private Integer ano;
}
