package com.paraparp.gestorfondos.model.entity;

import java.io.Serializable;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datePK == null) ? 0 : datePK.hashCode());
		result = prime * result + ((isinPK == null) ? 0 : isinPK.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoricalPK other = (HistoricalPK) obj;
		if (datePK == null) {
			if (other.datePK != null)
				return false;
		} else if (!datePK.equals(other.datePK))
			return false;
		if (isinPK == null) {
			if (other.isinPK != null)
				return false;
		} else if (!isinPK.equals(other.isinPK))
			return false;
		return true;
	}
}