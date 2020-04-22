package com.paraparp.gestorfondos.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.model.entity.Portfolio;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.model.entity.User;
import com.paraparp.gestorfondos.repository.ILotRepository;
import com.paraparp.gestorfondos.repository.IPortfolioRepository;
import com.paraparp.gestorfondos.repository.ISymbolRepository;
import com.paraparp.gestorfondos.repository.IUserRepository;
import com.paraparp.gestorfondos.service.IPortfolioService;
import com.paraparp.gestorfondos.service.ISymbolService;

@Service
public class SymbolService implements ISymbolService {

	
	@Autowired
	private ISymbolRepository symbolRepository;
	
	
	
	
	@Override
	public Symbol findById(Long id) {
		
		return null;
	}

	@Override
	public Symbol save(Symbol symbol) {

		return symbolRepository.save(symbol);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Symbol> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Symbol> findByIdUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Symbol findByIsin(String isin) {
		// TODO Auto-generated method stub
		return null;
	}



}
