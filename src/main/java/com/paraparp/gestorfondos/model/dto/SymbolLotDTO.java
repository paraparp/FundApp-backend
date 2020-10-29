package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.util.Util2;

import lombok.Data;

@Data
public class SymbolLotDTO {

	private Symbol symbol;

	@JsonIgnore
	private PortfolioDTO portfolio;

	@JsonIgnore
	private List<LotDTO> lots;

	public BigDecimal getVolume() {

		BigDecimal volume = BigDecimal.ZERO;
		if (lots != null) {
			volume = lots.stream().map(LotDTO::getVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return volume;
	}

	public BigDecimal getValue() {
		
		BigDecimal value = BigDecimal.ZERO;
		if (symbol.getLastPrice() != null)
			value = getVolume().multiply(symbol.getLastPrice());

		return (value.compareTo(BigDecimal.ZERO) != 0) ? value : getCost();
	}

	public BigDecimal getCost() {

		return lots.stream().map(LotDTO::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getPrice() {
		return Util2.utilDivideBD(getCost(), getVolume());
	}

	public BigDecimal getVariation() {
		return (getValue().subtract(getCost()));
	}

	public BigDecimal getPercentVariation() {
		return Util2.utilDivideBD(getVariation(), getCost());
	}

	public BigDecimal getPercentInPortfolio() {
		return Util2.utilDivideBD(getCost(), portfolio.getCost());
	}

	public BigDecimal getLastPercentInPortfolio() {
		return Util2.utilDivideBD(getValue(), portfolio.getValue());
	}
}
