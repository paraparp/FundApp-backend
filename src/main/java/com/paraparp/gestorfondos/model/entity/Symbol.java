package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

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

	private String bloomberg;

	private String url;

	@Column(name = "last_date")
	private String lastDate;

    @Column(name = "last_price",columnDefinition = "decimal(18,2) default 0")
	private BigDecimal lastPrice = BigDecimal.ZERO;

	private Date updated;

	private String category;

	private String location;

	private String type;

//	@Column(name = "creation_date")
//	@Temporal(TemporalType.DATE)
//	private Date creationDate;

}