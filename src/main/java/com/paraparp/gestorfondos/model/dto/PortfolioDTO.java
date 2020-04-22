package com.paraparp.gestorfondos.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paraparp.gestorfondos.model.entity.Lot;

//
//@JsonSerialize
public class PortfolioDTO {

	private long id;
	private String name;
	private String description;
	private String currency;
	private Long idUser;
	private List<LotDTO> lots;
	private Date creationDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public List<LotDTO> getLots() {
		return lots;
	}

	public void setLots(List<LotDTO> lots) {
		this.lots = lots;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Double getCost() {
		Double total = 0.0;
		for (LotDTO lot : lots) {
			total += lot.getTotalCost();
		}

		return total;

	}

	public Double getValue() {
		Double total = 0.0;
		for (LotDTO lot : lots) {
			total += lot.getTotalValue();
		}
		
		return total;

	}
	
	public Double getVariation() {
		return getValue() - getCost();
	}

	public Double getVariationPercent() {

		return (getCost()!=0.00)?((getValue() - getCost())/ getCost()):0.00;
	}
//
//	public Double getCost() {
//
//		Double total = 0.0;
//
//		for (Lot lot : lots) {
//			total += lot.getTotalCost();
//		}
//
//		return total;
//
//	}

//	public Double getValue() {
//
//		Double value = 0.00;
//		for (SymbolLotsDTO lot : lots) {
//			System.out.println(lot.getVolume());
//			System.out.println(lot.getSymbol().getLastPrice());
//			value += lot.getVolume() * lot.getSymbol().getLastPrice();
//		}
//
//		return value;
//	}

//	public Double getCost() {
//
//		Double cost = 0.00;
//		if(!lots.isEmpty() ) {
//			for (SymbolLotsDTO lot : lots) {
//				cost += lot.getVolume() * lot.getPrice();
//			}
//		}
//		return cost;
//
//	}



}
