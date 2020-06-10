package com.paraparp.gestorfondos.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class HistoricalPrice {

	private String Id;
	private List<HistoryDetail> historyDetail;

}
