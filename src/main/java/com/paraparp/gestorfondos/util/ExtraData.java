package com.paraparp.gestorfondos.util;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExtraData {

    private String name;
    private String isin;
    private BigDecimal lastPrice;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private String updatedAt;
    private String category;
    private String type;
    private String location;
    private String url;
    private BigDecimal dailyChange;
    private BigDecimal dailyChangePercent;
	private BigDecimal oneYear;
	private BigDecimal fiveYears;



    public ExtraData(String name, String isin, BigDecimal lastPrice, BigDecimal maxPrice, BigDecimal minPrice, String updatedAt,
                     String category, String type, String location, String url,BigDecimal dailyChange,BigDecimal dailyChangePercent, BigDecimal oneYear, BigDecimal fiveYears) {
        super();
        this.name = name;
        this.isin = isin;
        this.lastPrice = lastPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.updatedAt = updatedAt;
        this.category = category;
        this.type = type;
        this.location = location;
        this.url = url;
        this.dailyChange = dailyChange;
        this.dailyChangePercent = dailyChangePercent;
        this.oneYear = oneYear;
        this.fiveYears = fiveYears;
    }


}