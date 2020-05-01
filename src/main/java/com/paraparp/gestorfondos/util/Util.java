package com.paraparp.gestorfondos.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class Util {

	/**
	 * Bloomberg
	 */
	public static ExtraData getBloombergData(String url) {

		WebScraper ws = new WebScraper(url);

		BigDecimal lastPrice = new BigDecimal(ws.scrapData("priceText__1853e8a5"));
		BigDecimal maxPrice = new BigDecimal(ws.scrapData("textRight__9a2b77e6"));
		BigDecimal minPrice = new BigDecimal(ws.scrapData("textLeft__c99cf899"));
		String updatedAt = ws.scrapData("time__245ca7bb");

		return new ExtraData(lastPrice, maxPrice, minPrice, updatedAt, null, null, null);
	}

	/**
	 * Financial Times
	 */
	public static ExtraData getFTData(String isin) {

		String ftUrl = FT_URL + isin;

		WebScraper ws = new WebScraper(ftUrl);
		String name = ws.scrapDataByXpath(FT_XPATH_NAME);

		// Para el duplicados del nombre en los Amundi
		name = name.replace("Amundi Index Solutions - ", "").trim();

		String isinBD = isin.trim().toUpperCase();

		String categoryFull = ws.scrapData(FT_CLASS_CATEGORY);
		String category = StringUtils.substringBetween(categoryFull, "category", "Launch").trim();
		String type1 = ws.scrapDataByXpath(FT_XPATH_TYPE);
		String type = type1.substring(type1.lastIndexOf(' ') + 1);

		String location = ws.scrapDataByXpath(FT_XPATH_LOCATION);

		if (location.isEmpty()) {
			location = ws.scrapDataByXpath(FT_XPATH_LOCATION2);
		}

//		BigDecimal maxPrice = new BigDecimal(ws.scrapData("textRight__9a2b77e6"));
//		BigDecimal minPrice = new BigDecimal(ws.scrapData("textLeft__c99cf899"));
		// TODO solo estos valores serian necesarios en el update
		String updatedAt = ws.scrapData("mod-disclaimer").split("of")[1].replaceAll(". As", "").trim();
		updatedAt = updatedAt.length()<15 ?  updatedAt: "No Data";
		BigDecimal lastPrice = new BigDecimal(ws.scrapData(FT_CLASS_LASTPRICE).split(" ")[0]);
		
		return new ExtraData(name, isinBD, lastPrice, null, null, updatedAt, category, StringUtils.capitalize(type),
				location);
	}

	final static String FT_URL = "https://markets.ft.com/data/funds/tearsheet/summary?s=";
	final static String FT_CLASS_ISIN = "/html/body/div[3]/div[3]/section/div[3]/div/div/table[1]/tbody/tr[2]/td";
	final static String FT_XPATH_CATEGORY = "/html/body/div[3]/div[3]/section/div[3]/div/div/table[1]/tbody/tr[3]/td";
	final static String FT_CLASS_LASTPRICE = "mod-ui-data-list__value";
	final static String FT_CLASS_CATEGORY = "mod-ui-table mod-ui-table--two-column mod-profile-and-investment-app__table--profile";
	final static String FT_XPATH_LOCATION = "/html/body/div[3]/div[3]/section/div[4]/div/div[2]/div[4]/div[2]/table/tbody/tr[1]/td[1]/span";
	final static String FT_XPATH_LOCATION2 = "/html/body/div[3]/div[3]/section/div[4]/div/div[2]/div[3]/div[2]/table/tbody/tr[1]/td[1]/span";
	final static String FT_XPATH_TYPE = "/html/body/div[3]/div[3]/section/div[4]/div/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[1]/span";
	final static String FT_XPATH_NAME = "/html/body/div[3]/div[2]/section[1]/div/div/div[1]/div[1]/h1[1]";

}
