package com.paraparp.gestorfondos.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.model.entity.Symbol;

@JsonSerialize
public class SymbolLotsDTO {

	private Symbol symbol;

	@JsonIgnore
	private PortfolioDTO portfolio;
	
	@JsonIgnore
	private List<Lot> lots;

	public SymbolLotsDTO() {

	}

	public SymbolLotsDTO(Symbol symbol, PortfolioDTO portfolio, List<Lot> lots) {
		super();
		this.symbol = symbol;
		this.portfolio = portfolio;
		this.lots = lots;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public PortfolioDTO getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioDTO portfolio) {
		this.portfolio = portfolio;
	}


	public List<Lot> getLots() {
		return lots;
	}

	public void setLots(List<Lot> lots) {
		this.lots = lots;
	}

	public Double getVolume() {

		Double volume = 0.00;
		for (Lot lot : lots) {
			volume += lot.getVolume();
		}
		return volume;
	}

	public Double getValue() {
		return symbol.getLastPrice() != null ? getVolume() * symbol.getLastPrice(): 0.00;
	}

	public Double getCost() {

		Double cost = 0.00;
		for (Lot lot : lots) {
			cost += lot.getVolume() * lot.getPrice();
		}
		return cost;

	}

	public Double getPrice() {

		return getCost() / getVolume();
	}
	
	
	public Double getPercentVariation() {

		return  (getValue()-getCost()) /getCost();
	}
	
	public Double getPercentInPortfolio() {

		return  null;
	}

}
