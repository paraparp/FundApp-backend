package com.paraparp.gestorfondos.dto;

import java.util.Date;
import java.util.List;

import com.paraparp.gestorfondos.model.Lot;
import com.paraparp.gestorfondos.model.Symbol;

public class SymbolLotsDTO {

	private Symbol symbol;
	private Double lastPrice;
	private Date lastDate;
	private Long idPortfolio;
	private List<Lot> lots;

	public SymbolLotsDTO() {

	}

	public SymbolLotsDTO(Symbol symbol, Double lastPrice, Date lastDate, Long idPortfolio, List<Lot> lots) {
		super();
		this.symbol = symbol;
		this.lastPrice = lastPrice;
		this.lastDate = lastDate;
		this.idPortfolio = idPortfolio;
		this.lots = lots;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public Long getIdPortfolio() {
		return idPortfolio;
	}

	public void setIdPortfolio(Long idPortfolio) {
		this.idPortfolio = idPortfolio;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
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
		return getVolume() * lastPrice;
	}

	public Double getCost() {

		Double cost = 0.00;
		for (Lot lot : lots) {
			cost += lot.getVolume()*lot.getPrice();
		}
		return cost;

	}

	public Double getPrice() {

		return getCost() / getVolume();
	}

}
