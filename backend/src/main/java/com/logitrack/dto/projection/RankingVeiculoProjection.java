package com.logitrack.dto.projection;

import java.math.BigDecimal;

public interface RankingVeiculoProjection {

	Integer getId();

	String getPlaca();

	String getModelo();

	String getTipo();

	BigDecimal getTotalKm();
}
