package com.paraparp.gestorfondos.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.paraparp.gestorfondos.errors.exceptions.GenericNotFoundException;
import com.paraparp.gestorfondos.errors.exceptions.ResourceNotFoundException;
import com.paraparp.gestorfondos.model.dto.SymbolDTO;
import com.paraparp.gestorfondos.model.dto.converter.SymbolDTOConverter;
import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.repository.ISymbolRepository;
import com.paraparp.gestorfondos.service.ISymbolService;
import com.paraparp.gestorfondos.service.base.SymbolServicePag;
import com.paraparp.gestorfondos.service.imp.MorningStarService;
import com.paraparp.gestorfondos.service.imp.SymbolUpdaterService;
import com.paraparp.gestorfondos.util.pagination.PaginationLinksUtils;

import net.minidev.json.parser.ParseException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fundapp/symbols")
public class SymbolController {

	@Autowired
	private ISymbolRepository symbolRepository;

	@Autowired
	private ISymbolService symbolService;

	@Autowired
	private SymbolServicePag symbolServicePag;

	@Autowired
	private SymbolUpdaterService updaterService;

	@Autowired
	private SymbolDTOConverter symbolDTOConverter;

	@Autowired
	private PaginationLinksUtils paginationLinksUtils;

	@Autowired
	MorningStarService msService;



	@GetMapping("")
	public ResponseEntity<?> getAllSymbols() {
		List<Symbol> result = symbolRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(result);
		}
	}
	
	
	@GetMapping("/pag")
	public ResponseEntity<?> getAllSymbols(@PageableDefault(size = 5, page = 0) Pageable pageable,
			HttpServletRequest request) {
		Page<Symbol> result = symbolServicePag.findAll(pageable);

		if (result.getTotalPages() < pageable.getPageNumber())
			throw new GenericNotFoundException(Symbol.class, "Page number " + pageable.getPageNumber()
					+ " out of range. Total pages: " + result.getTotalPages());

		else if (result.isEmpty()) {
			throw new GenericNotFoundException(Symbol.class);
		} else {
			Page<SymbolDTO> symbolList = result.map(symbolDTOConverter::convertToDto);
			UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

			return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(symbolList, uriBuilder))
					.body(symbolList);
		}
	}

	
	@GetMapping("/{id}")
	public Symbol getSymbolById(@PathVariable Long id) throws ResourceNotFoundException {
//		Symbol result = symbolRepository.findById(id).orElse(null);
//
//		if (result == null) {
//			return ResponseEntity.notFound().build();
//		} else {
//			return ResponseEntity.ok().body(result);
//		}

		return symbolRepository.findById(id).orElseThrow(() -> new GenericNotFoundException(Symbol.class, id));
	}

//	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("")
	public ResponseEntity<Symbol> createSymbol(@Valid @RequestBody Symbol symbol) {
		Symbol saved = symbolService.save(symbol);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Symbol> updateSymbol(@Valid @RequestBody Symbol symbol, @PathVariable Long id) {

		return symbolRepository.findById(id).map(p -> {
			return ResponseEntity.ok(symbolRepository.save(p));
		}).orElseGet(() -> {
			return ResponseEntity.notFound().build();
		});
	}

	@Secured("ROLE_USER")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSymbol(@PathVariable Long id) throws ResourceNotFoundException {

		Symbol symbol = symbolRepository.findById(id).orElseThrow(() -> new GenericNotFoundException(Symbol.class, id));
		symbolRepository.delete(symbol);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/msinfo/{isin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getMorningStarID(@PathVariable String isin) throws IOException, JSONException, ParseException {

		return msService.getSymbolInfo(isin).toString();
	}

	@GetMapping("/{isin}/price")
	public BigDecimal getPriceByDate(@PathVariable(value = "isin") String isin,
			@RequestParam(value = "date", required = false) String date)
			throws IOException, JSONException, ParseException {

		return msService.getPriceDateMS(date, isin);
	}

	@GetMapping("/search/{isin}")
	public Symbol searchSymbol(@PathVariable(value = "isin") String isin) throws Exception {
		return this.updaterService.searchByIsin(isin);
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