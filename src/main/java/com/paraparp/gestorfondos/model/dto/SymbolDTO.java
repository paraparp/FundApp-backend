package com.paraparp.gestorfondos.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SymbolDTO {

	private long id;

	private String name;

	private String isin;

	private String morningstar;

	private String url;

	private String lastDate;

	private BigDecimal lastPrice;
	
	private LocalDate updated;

	private String category;

	private String location;

	private String type;

	private BigDecimal dailyChange;

	private BigDecimal dailyChangePercent;

	private BigDecimal oneYear;
	
	private BigDecimal fiveYears;
	
	private Date creationDate;
	
	//private List<Historical> historical;
	
	
}
