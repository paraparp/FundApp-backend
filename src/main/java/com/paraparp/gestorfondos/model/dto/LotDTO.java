package com.paraparp.gestorfondos.model.dto;

import java.util.Date;

import com.paraparp.gestorfondos.model.entity.Symbol;

import lombok.Data;

@Data
public class LotDTO {

	private long id;
	private Symbol symbol;
	private Double volume;
	private Double price;
	private String broker;
	private Date date;
	private Long idPortfolio;
	private Date creationDate;


	public Double getTotalValue() {
		return (symbol.getLastPrice() != null) ? volume * symbol.getLastPrice() : 0.00;
	}

	public Double getTotalCost() {

		return volume * price;

	}
	
}

