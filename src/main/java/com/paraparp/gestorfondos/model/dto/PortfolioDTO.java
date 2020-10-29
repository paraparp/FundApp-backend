package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.paraparp.gestorfondos.util.Util2;

import lombok.Data;

@Data
public class PortfolioDTO {

	private long id;
	private String name;
	private String description;
	private String currency;
	private Long idUser;

	private List<LotDTO> lots;
	private LocalDate creationDate;

	public BigDecimal getCost() {

		return lots.stream().map(LotDTO::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getValue() {

		BigDecimal totalValue =  lots.stream().map(LotDTO::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);

		return (totalValue.compareTo(BigDecimal.ZERO) != 0) ? totalValue : getCost();
	}

	public BigDecimal getVariation() {
		return getValue().subtract(getCost());
	}

	public BigDecimal getVariationPercent() {
		return Util2.utilDivideBD(getVariation(), getCost());
	}

}
