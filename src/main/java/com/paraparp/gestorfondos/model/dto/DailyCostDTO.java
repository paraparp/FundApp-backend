package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DailyCostDTO {

	@Temporal(TemporalType.DATE)
	private LocalDate date;

	private BigDecimal totalCost;

	private BigDecimal totalGain;
	
	private BigDecimal bondPercent;



	public DailyCostDTO(LocalDate date, BigDecimal totalCost, BigDecimal totalGain) {

		super();
		this.date = date;
		this.totalCost = totalCost;
		this.totalGain = totalGain;
	}

}
