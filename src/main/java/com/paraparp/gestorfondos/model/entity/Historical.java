package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "historical")
@IdClass(HistoricalPK.class)
public class Historical implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
	protected String isinPK;
	@Id
    protected String datePK;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	private Symbol symbol;

	@Column(name = "enddate")
	private LocalDate enddate;

	@Column(name = "price")
	private BigDecimal price = BigDecimal.ZERO;

}

