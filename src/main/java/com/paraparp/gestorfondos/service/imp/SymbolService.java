package com.paraparp.gestorfondos.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.ISymbolRepository;
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

		return symbolRepository.findAll();
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
