package com.paraparp.gestorfondos.util;

import java.io.IOException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

public class WebScraper {

	private String url;
	private Document htmlDocument;

	public WebScraper(String url) {
		this.url = url;
		this.htmlDocument = this.getHtmlDocument(url);
	}

	public String scrapData(String elementClass) {

		return htmlDocument.getElementsByClass(elementClass).text();
	}

	public String scrapDataByXpath(String xPath) {

		String data = "";

		W3CDom w3cDom = new W3CDom();
		org.w3c.dom.Document w3cDoc = w3cDom.fromJsoup(htmlDocument);

		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr;

		try {
			String response = xpath.evaluate(xPath, w3cDoc);
			if (response != null && !response.isEmpty()) {
				data = response;
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return data;

	}

	public Document getHtmlDocument(String url) {

		Document doc = null;
		try {
			Connection connection = Jsoup.connect(url).userAgent("Mozilla").timeout(200000);
			System.out.println("Connecting......");
			System.out.println(url);
			getStatusConnectionCode(connection);

			doc = connection.get();

		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		}

		return doc;
	}

	public int getStatusConnectionCode(Connection connection) {

		Response response = null;

		try {
			response = connection.ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
		}

		if (response.statusCode() != 200) {
			System.out.println("El Status Code no es OK es: " + response.statusCode());
		} else {
			System.out.println("200: OK");
		}

		return response.statusCode();
	}
}
