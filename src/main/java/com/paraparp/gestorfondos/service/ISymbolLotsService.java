package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.dto.SymbolLotsDTO;
import com.paraparp.gestorfondos.model.Portfolio;


@Service
public interface ISymbolLotsService {
	
	
	public List<SymbolLotsDTO> findByPortfolio(Long idPortfolio);
	



}
