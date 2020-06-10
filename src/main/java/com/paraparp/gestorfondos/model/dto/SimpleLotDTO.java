package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
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

	public SimpleLotDTO(LocalDate date, BigDecimal volume, BigDecimal price, BigDecimal total) {
		super();
		this.date = date;
		this.volume = volume;
		this.price = price;
		this.total = total;
	}

}
