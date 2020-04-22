package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "symbols")
public class Symbol implements Serializable {


	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="isin")
	private String isin;
	
	@Column(name="bloomberg")
	private String bloomberg;
	
	@Column(name="url")
	private String url;
	
	@Column(name="last_date")
	private String lastDate;
	
	@Column(name="last_price")
	private Double lastPrice;

	@Column(name="updated")
	private Date updated;
	
	@Column(name="category")
	private String category;
	
	@Column(name="location")
	private String location;
	
	@Column(name="type")
	private String type;
	
//	@Column(name = "creation_date")
//	@Temporal(TemporalType.DATE)
//	private Date creationDate;
	

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

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}



	public String getBloomberg() {
		return bloomberg;
	}

	public void setBloomberg(String bloomberg) {
		this.bloomberg = bloomberg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	
	
}