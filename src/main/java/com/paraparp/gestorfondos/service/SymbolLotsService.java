package com.paraparp.gestorfondos.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.dto.SymbolLotsDTO;
import com.paraparp.gestorfondos.model.Lot;
import com.paraparp.gestorfondos.model.Symbol;
import com.paraparp.gestorfondos.repository.ILotRepository;

@Service
public class SymbolLotsService implements ISymbolLotsService {

	@Autowired
	private PorfolioService porfolioSrv;

	@Autowired
	private ILotRepository lotRepo;

	@Override
	@Transactional(readOnly = true)
	public List<SymbolLotsDTO> findByPortfolio(Long idPortfolio) {

		List<Lot> lotsPorfolio = porfolioSrv.findById(idPortfolio).getLots();

		Set<Symbol> hashSet = new HashSet<Symbol>();
		for (Lot lot : lotsPorfolio) {
			hashSet.add(lot.getSymbol());
		}

		List<SymbolLotsDTO> symbolLots = new ArrayList<SymbolLotsDTO>();
		for (Symbol s : hashSet) {
			symbolLots.add(new SymbolLotsDTO(s, 0.00, null, idPortfolio,
					lotRepo.findBySymbolAndPortfolio(s, porfolioSrv.findById(idPortfolio))));
		}

		return symbolLots;
	}

}
