package com.paraparp.gestorfondos.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HistoryDetail {
	
	
	@JsonProperty("EndDate")
	private String EndDate;
	@JsonProperty("OriginalDate")
	private String OriginalDate;
	@JsonProperty("Value")
	private String Value;

}
