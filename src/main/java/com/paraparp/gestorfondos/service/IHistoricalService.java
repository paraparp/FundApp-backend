package com.paraparp.gestorfondos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.entity.Historical;
import com.paraparp.gestorfondos.model.entity.Symbol;


@Service
public interface IHistoricalService {
	
public Historical findBySymbolAndDate(Symbol symbol, LocalDate ld);

public Historical save(Historical historical);

public List<Historical> findByIsin(String isin);

}
