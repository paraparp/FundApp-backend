package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "symbols")
public class Symbol implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private String isin;

	private String morningstar;

	private String url;

	@Column(name = "last_date")
	private String lastDate;

	@Column(name = "last_price")
	@Digits(integer = 18, fraction = 4)
	private BigDecimal lastPrice = BigDecimal.ZERO;

	private LocalDate updated;

	private String category;

	private String location;

	private String type;

	@Column(name = "daily_change")
	@Digits(integer = 18, fraction = 4)
	private BigDecimal dailyChange = BigDecimal.ZERO;

	@Column(name = "daily_change_percent")
	@Digits(integer = 18, fraction = 4)
	private BigDecimal dailyChangePercent = BigDecimal.ZERO;

	@Column(name = "one_year")
	@Digits(integer = 18, fraction = 4)
	private BigDecimal oneYear = BigDecimal.ZERO;

	@Column(name = "five_years")
	@Digits(integer = 18, fraction = 4)
	private BigDecimal fiveYears = BigDecimal.ZERO;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "symbol_id")
	private List<Historical> historical;

	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date creationDate;

}