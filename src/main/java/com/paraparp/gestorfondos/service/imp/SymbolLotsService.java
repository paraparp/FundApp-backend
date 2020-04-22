package com.paraparp.gestorfondos.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.dto.SymbolLotsDTO;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.model.entity.Portfolio;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.ILotRepository;
import com.paraparp.gestorfondos.repository.ISymbolRepository;
import com.paraparp.gestorfondos.service.ISymbolLotsService;

@Service
public class SymbolLotsService implements ISymbolLotsService {

	@Autowired
	private PortfolioService porfolioSrv;

	@Autowired
	private ILotRepository lotRepo;
	
	@Autowired
	private ISymbolRepository symbolRepo;

	@Override
	@Transactional
	public List<SymbolLotsDTO> findByPortfolio(Long idPortfolio) {

		PortfolioDTO portfolio = porfolioSrv.findById(idPortfolio);
		List<LotDTO> lotsPorfolio = portfolio.getLots();
		Set<Symbol> hashSet = new HashSet<Symbol>();

		for (LotDTO lot : lotsPorfolio) {
			hashSet.add(lot.getSymbol());
		}

		List<SymbolLotsDTO> symbolLots = new ArrayList<SymbolLotsDTO>();

		for (Symbol s : hashSet) {

			List<Lot> lots = lotRepo.findBySymbolAndPortfolio(s, idPortfolio);
			
			symbolLots.add(new SymbolLotsDTO(s, portfolio, lots));

		}

		return symbolLots;


	}

}
