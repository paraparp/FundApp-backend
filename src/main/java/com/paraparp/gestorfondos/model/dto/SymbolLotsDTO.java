package com.paraparp.gestorfondos.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.model.entity.Symbol;

import lombok.Data;

@JsonSerialize
@Data
public class SymbolLotsDTO {

	private Symbol symbol;

	@JsonIgnore
	private PortfolioDTO portfolio;

	@JsonIgnore
	private List<Lot> lots;

	

	public Double getVolume() {

		Double volume = 0.00;
		for (Lot lot : lots) {
			volume += lot.getVolume();
		}
		return volume;
	}

	public Double getValue() {
		return symbol.getLastPrice() != null ? getVolume() * symbol.getLastPrice() : 0.00;
	}

	public Double getCost() {

		Double cost = 0.00;
		for (Lot lot : lots) {
			cost += lot.getVolume() * lot.getPrice();
		}
		return cost;

	}

	public Double getPrice() {

		return (getVolume()!= 0) ?(getCost() / getVolume()) :0.00;
	}

	public Double getPercentVariation() {

		return (getValue() - getCost()) / getCost();
	}

	public Double getPercentInPortfolio() {
		return getCost()/portfolio.getCost();
	}

	public Double getLastPercentInPortfolio() {

		return getValue()/portfolio.getValue();
	}
}
