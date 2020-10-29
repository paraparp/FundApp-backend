package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyCostDTO {

	@Temporal(TemporalType.DATE)
	private LocalDate date;

	private BigDecimal totalCost;

	private BigDecimal totalGain;

	private BigDecimal bondPercent;

}
