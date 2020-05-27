package com.paraparp.gestorfondos.service.imp;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.paraparp.gestorfondos.model.dto.HistoryDetail;
import com.paraparp.gestorfondos.service.IMorningStartService;
import com.paraparp.gestorfondos.util.UtilJSON;

@Service
public class MorningStarService implements IMorningStartService{

	ModelMapper modelMapper = new ModelMapper();

	public BigDecimal getPriceDate(String date, String isin) throws IOException, JSONException {
		
		String msID = getMorningStarID(isin);

		String ld = LocalDate.parse(date).plusDays(-3).toString();
		
	
		String URL_MORNINSTAR_HISTORICAL = "http://tools.morningstar.es/api/rest.svc/timeseries_price/2nhcdckzon?startdate=" + ld
				+ "&id=" + msID + "&frequency=daily&outputType=JSON&enddate=" + date;


		JSONObject json = UtilJSON.readJsonFromUrl(URL_MORNINSTAR_HISTORICAL);

		String json2 = JsonPath.read(Configuration.defaultConfiguration().jsonProvider().parse(json.toString()),
				"$.TimeSeries..HistoryDetail").toString();

		JSONArray jsonArray = (JSONArray) new JSONArray(json2).get(0);

		List<HistoryDetail> lista = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {

			ObjectMapper m = new ObjectMapper();
			HistoryDetail myClass = m.readValue(jsonArray.getJSONObject(i).toString(), HistoryDetail.class);
			lista.add(myClass);
			return new BigDecimal(myClass.getValue());

		}
		return new BigDecimal(lista.get(0).getValue());
	}

	@Override
	public BigDecimal getLastPrice(String isin) throws IOException, JSONException {
	
		
		//TODO no calcula bien el ultimo dia
		return this.getPriceDate(LocalDate.now().plusDays(-3).toString(), isin);
	}

	@Override
	public JSONArray getSymbolInfo(String isin) throws IOException, JSONException {
		
		String URL_MORNINSTAR_info = "https://lt.morningstar.com/api/rest.svc/klr5zyak8x/security/screener?outputType=json"
				+ "&securityDataPoints=isin%7COngoingCharge%7CSecId%7CName%7CPriceCurrency%7CLegalName%7CClosePrice%7CCategoryName"
				+ "%7CReturnD1%7CReturnW1%7CReturnM1%7CReturnM3%7CReturnM6%7CReturnM0%7CReturnM12%7CReturnM36%7CReturnM60"
				+ "%7CReturnM120%7CFeeLevel%7CMaxDeferredLoad%7CInitialPurchase&term=" + isin;
		
		JSONObject jsonInfo = UtilJSON.readJsonFromUrl(URL_MORNINSTAR_info);

		return jsonInfo.getJSONArray("rows");
	}
	
	public String getMorningStarID(String isin) throws IOException, JSONException {
		
		JSONArray symbolInfo = this.getSymbolInfo(isin);
		
		return symbolInfo.getJSONObject(0).getString("SecId");
		
//		return JsonPath.read(Configuration.defaultConfiguration().jsonProvider().parse(symbolInfo.toString()),
//				"$..SecId");
		
	}
	
}
