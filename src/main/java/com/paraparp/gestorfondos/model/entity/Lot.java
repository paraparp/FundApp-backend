package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lots")
public class Lot implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Symbol symbol;

	private Double volume;
	private Double price;
	private String broker;
	private Date date;

	@JsonIgnore
	@OneToOne
	private Portfolio portfolio;

	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "Lot [id=" + id + ", symbol=" + symbol + ", volume=" + volume + ", price=" + price + ", broker=" + broker
				+ ", date=" + date + ", portfolio=" + portfolio + ", creationDate=" + creationDate + "]";
	}

}
