package com.paraparp.gestorfondos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paraparp.gestorfondos.exception.ResourceNotFoundException;
import com.paraparp.gestorfondos.model.Symbol;
import com.paraparp.gestorfondos.repository.ISymbolRepository;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fundapp/symbols")
public class SymbolController 
{
	@Autowired
	private ISymbolRepository symbolRepository;

	@GetMapping("")
	public List<Symbol> getAllSymbols() {
		return symbolRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Symbol> getSymbolById(@PathVariable(value = "id") Long symbolId) throws ResourceNotFoundException 
	{
		Symbol symbol = this.checkSymbol(symbolId);
		return ResponseEntity.ok().body(symbol);
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("")
	public Symbol createSymbol(@Valid @RequestBody Symbol symbol) 
	{
		return symbolRepository.save(symbol);
	}

//	@Secured({"ROLE_ADMIN","ROLE_USER"})
//	@PutMapping("/{id}")
//	public ResponseEntity<Symbol> updateSymbol(@PathVariable(value = "id") Long symbolId,
//			@Valid @RequestBody Symbol symbolDetails) throws ResourceNotFoundException 
//	{
//		Symbol symbol = this.checkSymbol(symbolId);
//		
//		symbol.setEmail(symbolDetails.getEmail());
//		symbol.setLastName(symbolDetails.getLastName());
//		symbol.setFirstName(symbolDetails.getFirstName());
//		final Symbol updatedSymbol = symbolRepository.save(symbol);
//		return ResponseEntity.ok(updatedSymbol);
//	}

	@Secured("ROLE_USER")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteSymbol(@PathVariable(value = "id") Long symbolId) throws ResourceNotFoundException 
	{
		Symbol symbol = this.checkSymbol(symbolId);
		
		symbolRepository.delete(symbol);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	


	
	/**
	 * Busca el usuario y si no lo encuentra devuelve error
	 * @param symbolId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private Symbol checkSymbol(Long symbolId) throws ResourceNotFoundException {
		return symbolRepository.findById(symbolId)
				.orElseThrow(() -> new ResourceNotFoundException("Symbol not found for this id :: " + symbolId));
	
		
	}
}