package com.paraparp.gestorfondos.util;

public class ExtraData {

	private String name;
	private String isin;
	private Double lastPrice;
	private Double maxPrice;
	private Double minPrice;
	private String updatedAt;
	private String category;
	private String type;
	private String location;

	public ExtraData(Double lastPrice, Double maxPrice, Double minPrice, String updatedAt, String category, String type,
			String location) {
		super();
		this.lastPrice = lastPrice;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
		this.updatedAt = updatedAt;
		this.type = type;
		this.category = category;
		this.location = location;
	}

	public ExtraData(String name, String isin, Double lastPrice, Double maxPrice, Double minPrice, String updatedAt,
			String category, String type, String location) {
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
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

}