package com.paraparp.gestorfondos.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paraparp.gestorfondos.exception.ResourceNotFoundException;
import com.paraparp.gestorfondos.model.dto.DailyCostDTO;
import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.dto.SimpleLotDTO;
import com.paraparp.gestorfondos.model.dto.SymbolLotDTO;
import com.paraparp.gestorfondos.model.entity.Portfolio;
import com.paraparp.gestorfondos.repository.IPortfolioRepository;
import com.paraparp.gestorfondos.service.IPortfolioService;
import com.paraparp.gestorfondos.service.ISymbolLotsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fundapp/portfolios")
public class PortfolioController {
	@Autowired
	private IPortfolioRepository portfolioRepository;

	@Autowired
	private IPortfolioService portfolioService;

	@Autowired
	private ISymbolLotsService symbolLotsService;

	@GetMapping("")
	public List<PortfolioDTO> getAllPortfolios() {
		return portfolioService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PortfolioDTO> getPortfolioById(@PathVariable(value = "id") Long portfolioId)
			throws ResourceNotFoundException {
		PortfolioDTO portfolio = this.checkPortfolioDTO(portfolioId);
		return ResponseEntity.ok().body(portfolio);
	}

	@GetMapping("/{id}/lots")
	public ResponseEntity<List<LotDTO>> getLotsByPorfolio(@PathVariable(value = "id") Long portfolioId)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(portfolioService.findLotsByPorfolio(portfolioId));
	}

	@GetMapping("/{id}/lots/cost")
	public ResponseEntity<List<DailyCostDTO>> getCost(@PathVariable(value = "id") Long portfolioId)
			throws ResourceNotFoundException, IOException, JSONException {
		return ResponseEntity.ok().body(portfolioService.findCostPortfolio(portfolioId));
	}

	@GetMapping("/{id}/lots/date")
	public ResponseEntity<List<SymbolLotDTO>> getSymbolBeforeDate(@PathVariable(value = "id") Long portfolioId,
			@RequestParam(value = "enddate", required = false) String endDate) throws ResourceNotFoundException {
		return ResponseEntity.ok().body(symbolLotsService.findByPortfolioAndEndDate(portfolioId, endDate));
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<List<PortfolioDTO>> getPortfolioByIdUser(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		return ResponseEntity.ok().body(portfolioService.findByIdUser(userId));
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PostMapping("")
	public PortfolioDTO createPortfolio(@Valid @RequestBody PortfolioDTO portfolio) {
		return portfolioService.save(portfolio);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PatchMapping("")
	public ResponseEntity<PortfolioDTO> updatePortfolio(@Valid @RequestBody PortfolioDTO portfolio)
			throws ResourceNotFoundException {

		PortfolioDTO updatedPortfolio = portfolioService.save(portfolio);
		return ResponseEntity.ok(updatedPortfolio);
	}

	@Secured("ROLE_USER")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deletePortfolio(@PathVariable(value = "id") Long portfolioId)
			throws ResourceNotFoundException {
		Portfolio portfolio = this.checkPortfolio(portfolioId);

		portfolioRepository.delete(portfolio);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	// Portfolio lots grouped by Symbol
	@GetMapping("/watchlist/{id}")
	public ResponseEntity<List<SymbolLotDTO>> getPortfolioLotsById(@PathVariable(value = "id") Long portfolioId)
			throws ResourceNotFoundException {
		PortfolioDTO portfolio = this.checkPortfolioDTO(portfolioId);
		return ResponseEntity.ok().body(symbolLotsService.findByPortfolio(portfolioId));
	}

	@GetMapping("/watchlist/{id}/{broker}")
	public ResponseEntity<List<SymbolLotDTO>> getPortfolioLotsByIdandBroker(
			@PathVariable(value = "id") Long portfolioId, @PathVariable(value = "broker") String broker)
			throws ResourceNotFoundException {
		PortfolioDTO portfolio = this.checkPortfolioDTO(portfolioId);
		return ResponseEntity.ok().body(symbolLotsService.findByPortfolioAndBroker(portfolioId, broker));
	}

	@GetMapping("/watchlist/{id}/filters")
	public ResponseEntity<List<SymbolLotDTO>> getPortfolioLotsByIdandBrokerAndType(
			@PathVariable(value = "id") Long portfolioId,
			@RequestParam(value = "broker", required = false) String broker,
			@RequestParam(value = "type", required = false) String type) throws ResourceNotFoundException {

		PortfolioDTO portfolio = this.checkPortfolioDTO(portfolioId);
		return ResponseEntity.ok().body(symbolLotsService.groupByBrokerAndType(portfolioId, broker, type));
	}

	@GetMapping("/{id}/brokers")
	public List<String> getBrokersByPortfolio(@PathVariable(value = "id") Long portfolioId) {
		return portfolioService.listBrokersByPortfolio(portfolioId);
	}

	@GetMapping("/{id}/types")
	public List<String> getTypesByPortfolio(@PathVariable(value = "id") Long portfolioId) {
		return portfolioService.listTypesByPortfolio(portfolioId);
	}

	/**
	 * Busca el usuario y si no lo encuentra devuelve error
	 * 
	 * @param portfolioId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private PortfolioDTO checkPortfolioDTO(Long portfolioId) throws ResourceNotFoundException {
		return portfolioService.findById(portfolioId);
//				.orElseThrow(() -> new ResourceNotFoundException("Portfolio not found for this id :: " + portfolioId));
	}

	private Portfolio checkPortfolio(Long portfolioId) throws ResourceNotFoundException {
		return portfolioRepository.findById(portfolioId)
				.orElseThrow(() -> new ResourceNotFoundException("Portfolio not found for this id :: " + portfolioId));
	}
}