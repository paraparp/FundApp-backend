package com.paraparp.gestorfondos.service.imp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.dto.SymbolLotDTO;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.service.ILotService;
import com.paraparp.gestorfondos.service.ISymbolLotsService;

@Service
public class SymbolLotsService implements ISymbolLotsService {

	@Autowired
	private PortfolioService porfolioSrv;

	@Autowired
	private ILotService lotService;

	@Override
	@Transactional
	public List<SymbolLotDTO> findByPortfolio(Long idPortfolio) {

		PortfolioDTO portfolio = porfolioSrv.findById(idPortfolio);
		List<LotDTO> lotsPorfolio = portfolio.getLots();

		Set<Symbol> hashSet = new HashSet<Symbol>();
		lotsPorfolio.forEach(lot -> hashSet.add(lot.getSymbol()));

		List<SymbolLotDTO> symbolLots = new ArrayList<SymbolLotDTO>();

		for (Symbol symbol : hashSet) {

			List<LotDTO> lots = lotService.findBySymbolAndPortfolio(symbol, idPortfolio);

			SymbolLotDTO symbolLotsDTO = new SymbolLotDTO();
			symbolLotsDTO.setLots(lots);
			symbolLotsDTO.setPortfolio(portfolio);
			symbolLotsDTO.setSymbol(symbol);

			symbolLots.add(symbolLotsDTO);
		}

		return symbolLots;
	}

	public List<SymbolLotDTO> findByPortfolioAndBroker(Long idPortfolio, String broker) {

		PortfolioDTO portfolio = porfolioSrv.findById(idPortfolio);
		List<LotDTO> lotsPorfolio = portfolio.getLots();

		Set<Symbol> hashSet = new HashSet<Symbol>();
		lotsPorfolio.forEach(lot -> hashSet.add(lot.getSymbol()));

		List<SymbolLotDTO> symbolLots = new ArrayList<SymbolLotDTO>();

		for (Symbol symbol : hashSet) {

			List<LotDTO> lots = lotService.findBySymbolAndPortfolioAndBrokerAndType(symbol, idPortfolio, broker, null);

			SymbolLotDTO symbolLotsDTO = new SymbolLotDTO();
			symbolLotsDTO.setLots(lots);
			symbolLotsDTO.setPortfolio(portfolio);
			symbolLotsDTO.setSymbol(symbol);

			if (symbolLotsDTO.getLots().size() > 0)
				symbolLots.add(symbolLotsDTO);

		}

		return symbolLots;
	}

	@Override
	public List<SymbolLotDTO> groupByBrokerAndType(Long idPortfolio, String broker, String type) {

		PortfolioDTO portfolio = porfolioSrv.findById(idPortfolio);
		List<LotDTO> lotsPorfolio = portfolio.getLots();

		Set<Symbol> hashSet = new HashSet<Symbol>();
		lotsPorfolio.forEach(lot -> hashSet.add(lot.getSymbol()));

		List<SymbolLotDTO> symbolLots = new ArrayList<SymbolLotDTO>();

		for (Symbol symbol : hashSet) {

			List<LotDTO> lots = lotService.findBySymbolAndPortfolioAndBrokerAndType(symbol, idPortfolio, broker, type);

			SymbolLotDTO symbolLotsDTO = new SymbolLotDTO();
			symbolLotsDTO.setLots(lots);
			symbolLotsDTO.setPortfolio(portfolio);
			symbolLotsDTO.setSymbol(symbol);

			if (symbolLotsDTO.getLots().size() > 0)
				symbolLots.add(symbolLotsDTO);

		}

		return symbolLots;
	}

	@Override
	@Transactional
	public List<SymbolLotDTO> findByPortfolioAndEndDate(Long idPortfolio, String endDate) {
		
		

		PortfolioDTO portfolio = porfolioSrv.findById(idPortfolio);
		List<LotDTO> lotsPorfolio = portfolio.getLots();

		Set<Symbol> hashSet = new HashSet<Symbol>();
		lotsPorfolio.forEach(lot -> hashSet.add(lot.getSymbol()));

		List<SymbolLotDTO> symbolLots = new ArrayList<SymbolLotDTO>();

		for (Symbol symbol : hashSet) {

				List<LotDTO> lots = lotService.findBySymbolAndPortfolioBeforeDate(symbol, idPortfolio,LocalDate.parse(endDate));
		
				SymbolLotDTO symbolLotsDTO = new SymbolLotDTO();
				symbolLotsDTO.setLots(lots);
				symbolLotsDTO.setPortfolio(portfolio);
				symbolLotsDTO.setSymbol(symbol);

				symbolLots.add(symbolLotsDTO);

			
		}

		return symbolLots;
	}

}
