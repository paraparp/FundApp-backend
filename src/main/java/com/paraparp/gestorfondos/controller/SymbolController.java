package com.paraparp.gestorfondos.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paraparp.gestorfondos.exception.ResourceNotFoundException;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.ISymbolRepository;
import com.paraparp.gestorfondos.service.ISymbolService;
import com.paraparp.gestorfondos.service.imp.MorningStarService;
import com.paraparp.gestorfondos.service.imp.SymbolUpdaterService;

import net.minidev.json.parser.ParseException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fundapp/symbols")
public class SymbolController {
	@Autowired
	private ISymbolRepository symbolRepository;

	@Autowired
	private ISymbolService symbolService;

	@Autowired
	private SymbolUpdaterService updaterService;

	@Autowired
	MorningStarService msService;

	@GetMapping("/search/{isin}")
	public Symbol searchSymbol(@PathVariable(value = "isin") String isin) throws Exception {
		return this.updaterService.searchByIsin(isin);
	}

	@GetMapping("")
	public List<Symbol> getAllSymbols() {
		return symbolRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Symbol> getSymbolById(@PathVariable(value = "id") Long symbolId)
			throws ResourceNotFoundException {
		Symbol symbol = this.checkSymbol(symbolId);
		return ResponseEntity.ok().body(symbol);
	}

//	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("")
	public Symbol createSymbol(@Valid @RequestBody Symbol symbol) {
		return symbolService.save(symbol);
	}

	@RequestMapping(value = "/msinfo/{isin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getMorningStarID(@PathVariable(value = "isin") String isin)
			throws IOException, JSONException, ParseException {

		return msService.getSymbolInfo(isin).toString();
	}

	@Secured("ROLE_USER")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteSymbol(@PathVariable(value = "id") Long symbolId)
			throws ResourceNotFoundException {
		Symbol symbol = this.checkSymbol(symbolId);

		symbolRepository.delete(symbol);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@GetMapping( "/{isin}/price")
	public BigDecimal getPriceByDate(@PathVariable(value = "isin") String isin,
			@RequestParam(value = "date", required = false) String date
			) throws IOException, JSONException, ParseException {

		return 	 msService.getPriceDateMS( date,isin);
	}
	

	/**
	 * Busca el usuario y si no lo encuentra devuelve error
	 * 
	 * @param symbolId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private Symbol checkSymbol(Long symbolId) throws ResourceNotFoundException {
		return symbolRepository.findById(symbolId)
				.orElseThrow(() -> new ResourceNotFoundException("Symbol not found for this id :: " + symbolId));

	}
}