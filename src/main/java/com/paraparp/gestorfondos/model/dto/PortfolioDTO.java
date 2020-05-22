package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private Date creationDate;

	public BigDecimal getCost() {

		BigDecimal total = BigDecimal.ZERO;
		for (LotDTO lot : lots) {
			total = total.add(lot.getCost());
		}

		return total;
	}

	public BigDecimal getValue() {

		BigDecimal total = BigDecimal.ZERO;
		for (LotDTO lot : lots) {
			total = total.add(lot.getValue());
		}

		return (total.compareTo(BigDecimal.ZERO) != 0) ? total : getCost();
	}

	public BigDecimal getVariation() {
		return getValue().subtract(getCost());
	}

	public BigDecimal getVariationPercent() {
		return Util2.utilDivideBD(getVariation(), getCost());
	}

}
