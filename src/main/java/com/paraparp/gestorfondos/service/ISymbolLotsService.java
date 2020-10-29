package com.paraparp.gestorfondos.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.SymbolLotDTO;

@Service
public interface ISymbolLotsService {

	public List<SymbolLotDTO> findByPortfolio(Long idPortfolio);
	
	public List<SymbolLotDTO> findByPortfolioAndBroker(Long idPortfolio,String broker);
	
	public List<SymbolLotDTO> groupByBrokerAndType(Long idPortfolio,String broker, String type);

	public List<SymbolLotDTO> findByPortfolioAndEndDate(Long idPortfolio, String endDate) throws IOException, JSONException;


	
	
}
