package com.paraparp.gestorfondos.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.SymbolDTO;
import com.paraparp.gestorfondos.model.entity.Symbol;

@Service
public interface ISymbolService {

	public List<SymbolDTO> findAll();
	
	public SymbolDTO findById(Long id);

	public List<Symbol> findByIdUser(Long userId);

	public Symbol findByIsin(String  isin);

	public Symbol save(Symbol portfolio);
	
	public Symbol update(@Valid Symbol symbol);

	public void deleteById(Long id);

}
