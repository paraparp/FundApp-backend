package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.entity.Symbol;

@Service
public interface ISymbolService {

	public Symbol findById(Long id);

	public Symbol save(Symbol portfolio);

	public void deleteById(Long id);

	public List<Symbol> findAll();

	public List<Symbol> findByIdUser(Long userId);

	public Symbol findByIsin(String  isin);
	


}
