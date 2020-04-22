package com.paraparp.gestorfondos.model.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paraparp.gestorfondos.model.entity.Symbol;

@JsonSerialize
public class LotDTO {

	private long id;
	private Symbol symbol;
	private Double volume;
	private Double price;
	private String broker;
	private Date date;
	private Long idPortfolio;
	private Date creationDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getIdPortfolio() {
		return idPortfolio;
	}

	public void setIdPortfolio(Long idPortfolio) {
		this.idPortfolio = idPortfolio;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

	public Double getTotalValue() {
		return (symbol.getLastPrice() != null) ? volume * symbol.getLastPrice() : 0.00;
	}

	public Double getTotalCost() {

		return volume * price;

	}

	@Override
	public String toString() {
		return "LotDTO [id=" + id + ", symbol=" + symbol + ", volume=" + volume + ", price=" + price + ", broker="
				+ broker + ", idPortfolio=" + idPortfolio + "]";
	}

	
}

