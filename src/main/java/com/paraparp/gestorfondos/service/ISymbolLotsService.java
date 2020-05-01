package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.SymbolLotDTO;

@Service
public interface ISymbolLotsService {

	public List<SymbolLotDTO> findByPortfolio(Long idPortfolio);

}
