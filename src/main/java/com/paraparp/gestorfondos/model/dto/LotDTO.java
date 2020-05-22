package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.paraparp.gestorfondos.model.entity.Symbol;

import lombok.Data;

@Data
public class LotDTO {

	private long id;
	private Symbol symbol;
	private BigDecimal volume;
	private BigDecimal price;
	private String broker;
	private LocalDate date;
	private Long idPortfolio;
	private Date creationDate;


	public BigDecimal getValue() {
		return  volume.multiply(symbol.getLastPrice());
	}

	public BigDecimal getCost() {
		return volume.multiply(price);
	}
	
}

