package com.paraparp.gestorfondos.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.SymbolDTO;
import com.paraparp.gestorfondos.model.dto.converter.SymbolDTOConverter;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.ISymbolRepository;
import com.paraparp.gestorfondos.service.ISymbolService;

@Service
public class SymbolService implements ISymbolService {

	@Autowired
	private SymbolDTOConverter symbolDTOConv;

	@Autowired
	private ISymbolRepository symbolRepository;

	@Override
	public List<SymbolDTO> findAll() {

		return symbolRepository.findAll().stream().map(symbolDTOConv::convertToDto).collect(Collectors.toList());
	}

	@Override
	public SymbolDTO findById(Long id) {
		Optional<Symbol> symbol = symbolRepository.findById(id);

		if (symbol.isPresent()) {
			return symbolDTOConv.convertToDto(symbol.get());
		} else {
			return null;
		}

	}

	@Override
	public Symbol save(Symbol symbol) {
		return symbolRepository.save(symbol);
	}

	@Override
	public void deleteById(Long id) {
		symbolRepository.deleteById(id);
	}

	@Override
	public List<Symbol> findByIdUser(Long userId) {
		// TODO
		return null;
	}

	@Override
	public Symbol findByIsin(String isin) {
		return symbolRepository.findByIsin(isin);
	}

	@Override
	public @Valid Symbol update(@Valid Symbol symbol) {

		return symbolRepository.save(symbol);
	}

}
