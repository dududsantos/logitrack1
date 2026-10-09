package com.logitrack.dto.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CronogramaManutencaoProjection {

	Integer getId();

	String getPlaca();

	String getModelo();

	String getTipoServico();

	LocalDate getDataInicio();

	LocalDate getDataFinalizacao();

	BigDecimal getCustoEstimado();

	String getStatus();
}
