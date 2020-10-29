package com.paraparp.gestorfondos.service.imp;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.paraparp.gestorfondos.model.dto.HistoryDetail;
import com.paraparp.gestorfondos.model.dto.SymbolDTO;
import com.paraparp.gestorfondos.model.entity.Historical;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.service.IHistoricalService;
import com.paraparp.gestorfondos.service.IMorningStartService;
import com.paraparp.gestorfondos.service.ISymbolService;
import com.paraparp.gestorfondos.util.ExtraData;
import com.paraparp.gestorfondos.util.Util;
import com.paraparp.gestorfondos.util.UtilJSON;

@Service
public class MorningStarService implements IMorningStartService {

	private static final String URL_MORNINGSTAR_TIMESERIES = "http://tools.morningstar.es/api/rest.svc/timeseries_price/2nhcdckzon?";
	private static final String HISTORY_DETAIL_PATH = "$.TimeSeries..HistoryDetail";
	private static final String FROM_DATE = "2018-01-01";

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IHistoricalService historicalService;

	@Autowired
	private ISymbolService symbolService;

	public int getAllSymbolsHistorical() throws IOException, JSONException {

		List<SymbolDTO> symbolList = symbolService.findAll();
		for (SymbolDTO symbol : symbolList) {
			this.getHistoricalByIsin(symbol.getIsin());
		}
		
		return symbolList.size();
	}

	public String getHistoricalByIsin(String isin) throws IOException, JSONException {

		Symbol symbol = symbolService.findByIsin(isin);

		LocalDate endLd = getFridayOfThisWeek(LocalDate.now());
		LocalDate startLd = getFridayOfThisWeek(LocalDate.parse(FROM_DATE));

		String url = URL_MORNINGSTAR_TIMESERIES
				+ "id="
				+ this.getMorningStarID(isin) + "&frequency=weekly&outputType=JSON&startdate=" + startLd + "&enddate="
				+ endLd;

		JSONObject json = UtilJSON.readJsonFromUrl(url);

		String json2 = JsonPath.read(Configuration.defaultConfiguration().jsonProvider().parse(json.toString()),
				HISTORY_DETAIL_PATH).toString();

		JSONArray jsonArra = (JSONArray) new JSONArray(json2);

		if (jsonArra.length() != 0) {

			JSONArray jsonArraySymbol = (JSONArray) new JSONArray(json2).get(0);

			for (int i = 0; i < jsonArraySymbol.length(); i++) {

				String stdate = jsonArraySymbol.getJSONObject(i).getString("EndDate");
				String stvalue = jsonArraySymbol.getJSONObject(i).getString("Value");
				Historical newHist = new Historical();

				newHist.setEnddate(LocalDate.parse(stdate));
				newHist.setPrice(new BigDecimal(stvalue));
				newHist.setSymbol(symbol);
				newHist.setIsinPK(isin);
				newHist.setDatePK(stdate);
				Historical hist = historicalService.findBySymbolAndDate(symbol, LocalDate.parse(stdate));

				if (hist == null)
					historicalService.save(newHist);
			}
		}
		return "Updated";

	}

	public BigDecimal getPriceDateMS(String date, String isin) throws IOException, JSONException {

		String msID = getMorningStarID(isin);

		String URL_MORNINSTAR_HISTORICAL = URL_MORNINGSTAR_TIMESERIES
				+ "startdate="
				+ date + "&id=" + msID + "&frequency=daily&outputType=JSON&enddate=" + date;

		JSONObject json = UtilJSON.readJsonFromUrl(URL_MORNINSTAR_HISTORICAL);

		String json2 = JsonPath.read(Configuration.defaultConfiguration().jsonProvider().parse(json.toString()),
				HISTORY_DETAIL_PATH).toString();

		JSONArray jsonArray = null;
		try {
			jsonArray = (JSONArray) new JSONArray(json2).get(0);
		} catch (Exception e) {

			return this.getLastPrice(isin);
		}

		List<HistoryDetail> lista = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {

			ObjectMapper m = new ObjectMapper();
			HistoryDetail myClass = m.readValue(jsonArray.getJSONObject(i).toString(), HistoryDetail.class);
			lista.add(myClass);
			return new BigDecimal(myClass.getValue());

		}
		return new BigDecimal(lista.get(0).getValue());
	}

	public BigDecimal getPriceDate(String date, String isin) throws IOException, JSONException {

		String msID = getMorningStarID(isin);

		String ld = LocalDate.parse(date).plusDays(-3).toString();

		String URL_MORNINSTAR_HISTORICAL = URL_MORNINGSTAR_TIMESERIES
				+ "startdate="
				+ ld + "&id=" + msID + "&frequency=daily&outputType=JSON&enddate=" + date;

		JSONObject json = UtilJSON.readJsonFromUrl(URL_MORNINSTAR_HISTORICAL);

		String json2 = JsonPath.read(Configuration.defaultConfiguration().jsonProvider().parse(json.toString()),
				HISTORY_DETAIL_PATH).toString();

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
	public JSONArray getSymbolInfo(String isin) throws IOException, JSONException {

		String URL_MORNINSTAR_info = "https://lt.morningstar.com/api/rest.svc/klr5zyak8x/security/screener?outputType=json"
				+ "&securityDataPoints=isin%7COngoingCharge%7CSecId%7CName%7CPriceCurrency%7CLegalName%7CClosePrice%7CCategoryName"
				+ "%7CReturnD1%7CReturnW1%7CReturnM1%7CReturnM3%7CReturnM6%7CReturnM0%7CReturnM12%7CReturnM36%7CReturnM60"
				+ "%7CReturnM120%7CFeeLevel%7CMaxDeferredLoad%7CInitialPurchase&term=" + isin;

		JSONObject jsonInfo = UtilJSON.readJsonFromUrl(URL_MORNINSTAR_info);

		return (jsonInfo.length() != 0) ? jsonInfo.getJSONArray("rows") : null;
	}

	public String getMorningStarID(String isin) throws IOException, JSONException {

		JSONArray symbolInfo = this.getSymbolInfo(isin);
		return (symbolInfo.length() != 0) ? symbolInfo.getJSONObject(0).getString("SecId") : null;

	}

	public String pickMorningStarParam(String isin, String param) throws IOException, JSONException {

		JSONArray symbolInfo = this.getSymbolInfo(isin);
		return (symbolInfo.length() != 0) ? symbolInfo.getJSONObject(0).getString(param) : null;

	}

	@Override
	public BigDecimal getLastPrice(String isin) throws IOException, JSONException {

		JSONArray symbolInfo = this.getSymbolInfo(isin);
		return (symbolInfo.length() != 0) ? new BigDecimal(symbolInfo.getJSONObject(0).getString("ClosePrice"))
				: BigDecimal.ZERO;
	}

	public static LocalDate getFridayOfThisWeek(LocalDate start) {

		DayOfWeek dowOfStart = start.getDayOfWeek();
		int difference = DayOfWeek.FRIDAY.getValue() - dowOfStart.getValue();
		if (difference < 0)
			difference += 7;

		return start.plusDays(difference);
	}

	public ExtraData getMorningStarData(String isin) throws IOException, JSONException {

		String name = pickMorningStarParam(isin, "Name");

		String isinBD = pickMorningStarParam(isin, "isin").toUpperCase();
		String categoryFull = pickMorningStarParam(isin, "CategoryName");
		String category = StringUtils.substringBetween(categoryFull, "category", "Launch").trim();
		String type = Util.extractFundTypeFromCategory(category);

		String location = "";

		// TODO solo estos valores serian necesarios en el update
		String updatedAt = "";
		updatedAt = updatedAt.length() < 15 ? updatedAt : "No Data";
		String lastP = pickMorningStarParam(isin, "ClosePrice");
		BigDecimal lastPrice = new BigDecimal(lastP);

		BigDecimal dailyChange = BigDecimal.ZERO;
		BigDecimal dailyChangePercent = BigDecimal.ZERO;

		String msUrl = "https://lt.morningstar.com/ova4sc327n/snapshot/default.aspx?tab=0&Id=" + isin
				+ "&ClientFund=0&CurrencyId=eur";

		BigDecimal oneYear = new BigDecimal(pickMorningStarParam(isin, "ReturnM12"));

//		BigDecimal fiveYears =  stringPercentToBigDecimal(wp.scrapDataByXpath("/html/body/div[3]/div[3]/section/div[1]/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/td[2]/span"));

		return new ExtraData(name, isinBD, lastPrice, null, null, updatedAt, category, StringUtils.capitalize(type),
				location, msUrl, dailyChange, dailyChangePercent, oneYear, null);
	}

}
