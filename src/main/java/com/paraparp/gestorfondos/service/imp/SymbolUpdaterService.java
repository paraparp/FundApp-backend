package com.paraparp.gestorfondos.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.ISymbolRepository;
import com.paraparp.gestorfondos.util.ExtraData;
import com.paraparp.gestorfondos.util.Util;

@Service
public class SymbolUpdaterService {

	@Autowired
	private ISymbolRepository respository;

	public String updater() {

		List<Symbol> symbols = respository.findAll();
		int updated = 0;

		for (Symbol symbol : symbols) {

			ExtraData bd = Util.getFTData(symbol.getIsin());

			if (bd != null) {
				symbol.setLastPrice(bd.getLastPrice());
//			symbol.setMaxPrice(bd.getMaxPrice());
//			symbol.seMinPrice(bd.getMinPrice());
				symbol.setLastDate(bd.getUpdatedAt());
				symbol.setUpdated(new Date());
				symbol.setCategory(bd.getCategory());
				symbol.setType(bd.getType());
				symbol.setLocation(bd.getLocation());

				updated++;
			}
		}
		respository.saveAll(symbols);

		return "Symbols : " + symbols.size() + " ===> Updated :  " + updated;
	}

	public Symbol searchByIsin(String isin) {

		// TODO por si el simbolo ya existe
		Symbol symbolBack = respository.findByIsin(isin);

		if (symbolBack != null) {
			System.out.println("Symbol already exists " + symbolBack.getIsin());
			return null;
		}

		ExtraData bd = null;
		try {
			bd = Util.getFTData(isin);
		} catch (Exception e) {
			return null;
		}

		if (bd != null) {
			Symbol symbol = new Symbol();
			symbol.setName(bd.getName());
			symbol.setIsin(bd.getIsin());
			symbol.setLastPrice(bd.getLastPrice());
//		symbol.setMaxPrice(bd.getMaxPrice());
//		symbol.seMinPrice(bd.getMinPrice());
			symbol.setLastDate(bd.getUpdatedAt());
			symbol.setUpdated(new Date());
			symbol.setCategory(bd.getCategory());
			symbol.setType(bd.getType());
			symbol.setLocation(bd.getLocation());

			return symbol;
		}
		return null;

	}

}
