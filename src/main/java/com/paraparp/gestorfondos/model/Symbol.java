package com.paraparp.gestorfondos.model;

import java.io.Serializable;

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

}