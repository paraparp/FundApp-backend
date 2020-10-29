package com.paraparp.gestorfondos.model.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.paraparp.gestorfondos.model.dto.SymbolDTO;
import com.paraparp.gestorfondos.model.entity.Symbol;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SymbolDTOConverter {

	private final ModelMapper modelMapper;
	
	public SymbolDTO convertToDto (Symbol symbol) {
		
		return modelMapper.map(symbol, SymbolDTO.class);
	}


	
}
