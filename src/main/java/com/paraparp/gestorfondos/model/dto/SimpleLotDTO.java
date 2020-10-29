package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleLotDTO {

	@Temporal(TemporalType.DATE)
	private LocalDate date;
	@JsonIgnore
	private BigDecimal volume;
	@JsonIgnore
	private BigDecimal price;
	private BigDecimal total;

	@JsonIgnore
	public BigDecimal getCost() {
		return volume.multiply(price);
	}



}
