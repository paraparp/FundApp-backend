package com.paraparp.gestorfondos.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.entity.Historical;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.IHistoricalRepository;
import com.paraparp.gestorfondos.service.IHistoricalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistoricalService implements IHistoricalService {

	
	private final IHistoricalRepository historicalRepository;

	@Override
	public Historical findBySymbolAndDate(Symbol symbol, LocalDate ld) {
		return this.historicalRepository.findBySymbolAndDate(symbol, ld);
	}

	@Override
	public Historical save(Historical historical) {
		return this.historicalRepository.save(historical);
	}

	@Override
	public List<Historical> findByIsin(String isin) {
		return this.historicalRepository.findByIsin(isin);
	}

}
