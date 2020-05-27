package com.paraparp.gestorfondos.service;

import java.io.IOException;
import java.math.BigDecimal;

import org.codehaus.jettison.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public interface IMorningStartService {

	public BigDecimal getPriceDate(String date, String isin) throws IOException, JSONException;

	public BigDecimal getLastPrice(String isin) throws IOException, JSONException;

	public String getMorningStarID(String isin) throws IOException, JSONException;

	public Object getSymbolInfo(String isin) throws IOException, JSONException;

}
