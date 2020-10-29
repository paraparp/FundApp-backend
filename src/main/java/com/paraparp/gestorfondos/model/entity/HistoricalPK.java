package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class HistoricalPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String isinPK;
    protected String datePK;

    public HistoricalPK() {}

    public HistoricalPK(String isinPK, String datePK) {
        this.isinPK = isinPK;
        this.datePK = datePK;
    }
  

	public String getIsinPK() {
		return isinPK;
	}

	public void setIsinPK(String isinPK) {
		this.isinPK = isinPK;
	}

	public String getDatePK() {
		return datePK;
	}

	public void setDatePK(String datePK) {
		this.datePK = datePK;
	}


}