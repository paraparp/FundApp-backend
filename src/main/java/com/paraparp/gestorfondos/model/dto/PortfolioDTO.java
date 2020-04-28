package com.paraparp.gestorfondos.model.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.paraparp.gestorfondos.model.entity.Lot;

import lombok.Data;


@Data
public class PortfolioDTO {

	
	private long id;
	private String name;
	private String description;
	private String currency;
	private Long idUser;
	private List<LotDTO> lots;
	private Date creationDate;

	
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


}
