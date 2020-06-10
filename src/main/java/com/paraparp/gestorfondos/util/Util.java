package com.paraparp.gestorfondos.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.paraparp.gestorfondos.service.IMorningStartService;

public class Util {

	/**
	 * Bloomberg
	 * 
	 * public static ExtraData getBloombergData(String url) {
	 * 
	 * WebScraper ws = new WebScraper(url);
	 * 
	 * BigDecimal lastPrice = new BigDecimal(ws.scrapData("priceText__1853e8a5"));
	 * BigDecimal maxPrice = new BigDecimal(ws.scrapData("textRight__9a2b77e6"));
	 * BigDecimal minPrice = new BigDecimal(ws.scrapData("textLeft__c99cf899"));
	 * String updatedAt = ws.scrapData("time__245ca7bb");
	 * 
	 * return new ExtraData(lastPrice, maxPrice, minPrice, updatedAt, null, null,
	 * null,null,null,null); }
	 */

	/**
	 * Financial Times
	 */
	public static ExtraData getFTData(String isin) {

		String ftUrl = PREFIX_URL_FT + isin;

		WebScraper ws = new WebScraper(ftUrl);
		String name = extractName(ws);

		String isinBD = isin.trim().toUpperCase();
		String categoryFull = ws.scrapData(FT_CLASS_CATEGORY);
		String category = StringUtils.substringBetween(categoryFull, "category", "Launch").trim();
		String typeFull = ws.scrapDataByXpath(FT_XPATH_TYPE);
		String type = extractFundTypeFromCategory(category);

		String location = ws.scrapDataByXpath(FT_XPATH_LOCATION);
		if (location.isEmpty()) {
			location = ws.scrapDataByXpath(FT_XPATH_LOCATION2);
		}

		// TODO solo estos valores serian necesarios en el update
		String updatedAt = ws.scrapData("mod-disclaimer").split("of")[1].replaceAll(". As", "").trim();
		updatedAt = updatedAt.length() < 15 ? updatedAt : "No Data";
		String lastP = ws.scrapData(FT_CLASS_LASTPRICE).replace(",", "");
		BigDecimal lastPrice = new BigDecimal(lastP.split(" ")[0]);

		BigDecimal dailyChange = new BigDecimal(ws.scrapDataByXpath(FT_XPATH_DAILY_CHANGE).split("/")[0].trim());
		BigDecimal dailyChangePercent = stringPercentToBigDecimal(
				ws.scrapDataByXpath(FT_XPATH_DAILY_CHANGE).split("/")[1]);

		// TODO podemos pillar los valores de morningstar
//		WebScraper wp = new WebScraper(PREFIX_URL_FT_PERF + isin);
//		BigDecimal oneYear = stringPercentToBigDecimal(wp.scrapDataByXpath("/html/body/div[3]/div[3]/section/div[1]/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/td[4]/span"));

//		BigDecimal fiveYears =  stringPercentToBigDecimal(wp.scrapDataByXpath("/html/body/div[3]/div[3]/section/div[1]/div/div[2]/div[2]/div[2]/table/tbody/tr[1]/td[2]/span"));

		return new ExtraData(name, isinBD, lastPrice, null, null, updatedAt, category, StringUtils.capitalize(type),
				location, ftUrl, dailyChange, dailyChangePercent, null, null);
	}

	public static String extractFundTypeFromCategory(String categoryFull) {

		String type = "";

		if (categoryFull.contains("Property")) {
			type = "Real State";
		} else if (categoryFull.contains("Bond")) {
			type = "Bond";
		} else if (categoryFull.contains("Equity") || categoryFull.contains("Stock")) {
			type = "Stock";
		} else {
//			type=  categoryFull.substring(categoryFull.lastIndexOf(' ') + 1);
			type = "Stock";
		}

		return type;
	}

	private static String extractName(WebScraper ws) {

		String name = ws.scrapDataByXpath(FT_XPATH_NAME);
		// Para el duplicados del nombre en los Amundi
		name = name.replace("Amundi Index Solutions - ", "").trim();
		// Para acortar el nombre
		name = name.replace("Accumulation", "Acc").replace(" Investor", "").replace(" Fund", "")
				.replace("Institutional ", "Inst").trim();

		return name;
	}

	private static BigDecimal stringPercentToBigDecimal(String stPercent) {

		return new BigDecimal(stPercent.replace("%", "").trim()).divide(BigDecimal.valueOf(100));
	}

	final static String PREFIX_URL_FT = "https://markets.ft.com/data/funds/tearsheet/summary?s=";
	final static String PREFIX_URL_FT_PERF = "https://markets.ft.com/data/funds/tearsheet/performance?s=";
	final static String FT_CLASS_ISIN = "/html/body/div[3]/div[3]/section/div[3]/div/div/table[1]/tbody/tr[2]/td";
	final static String FT_XPATH_CATEGORY = "/html/body/div[3]/div[3]/section/div[3]/div/div/table[1]/tbody/tr[3]/td";
	final static String FT_CLASS_LASTPRICE = "mod-ui-data-list__value";
	final static String FT_CLASS_CATEGORY = "mod-ui-table mod-ui-table--two-column mod-profile-and-investment-app__table--profile";
	final static String FT_XPATH_LOCATION = "/html/body/div[3]/div[3]/section/div[4]/div/div[2]/div[4]/div[2]/table/tbody/tr[1]/td[1]/span";
	final static String FT_XPATH_LOCATION2 = "/html/body/div[3]/div[3]/section/div[4]/div/div[2]/div[3]/div[2]/table/tbody/tr[1]/td[1]/span";
	final static String FT_XPATH_TYPE = "/html/body/div[3]/div[3]/section/div[4]/div/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[1]/span";
	final static String FT_XPATH_NAME = "/html/body/div[3]/div[2]/section[1]/div/div/div[1]/div[1]/h1[1]";
	final static String FT_XPATH_DAILY_CHANGE = "/html/body/div[3]/div[2]/section[1]/div/div/div[1]/div[2]/ul/li[2]/span[2]/span/text()";
	final static String FT_XPATH_ONE_YEAR_CHANGE = "/html/body/div[3]/div[2]/section[1]/div/div/div[1]/div[2]/ul/li[3]/span[2]/span";

}
