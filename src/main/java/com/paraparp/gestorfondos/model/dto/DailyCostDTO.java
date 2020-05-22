package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


public class DailyCostDTO {
	
	

	@Temporal(TemporalType.DATE)
	private LocalDate date;
	
	private BigDecimal total;

	public DailyCostDTO(LocalDate date, BigDecimal total) {
		super();
		this.date = date;
		this.total = total;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}




}
