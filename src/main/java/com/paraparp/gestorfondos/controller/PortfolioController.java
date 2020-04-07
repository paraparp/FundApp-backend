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

import com.paraparp.gestorfondos.dto.SymbolLotsDTO;
import com.paraparp.gestorfondos.exception.ResourceNotFoundException;
import com.paraparp.gestorfondos.model.Portfolio;
import com.paraparp.gestorfondos.repository.IPortfolioRepository;
import com.paraparp.gestorfondos.service.ISymbolLotsService;
import com.paraparp.gestorfondos.service.SymbolLotsService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fundapp/portfolios")
public class PortfolioController 
{
	@Autowired
	private IPortfolioRepository portfolioRepository;
	
	@Autowired
	private ISymbolLotsService symbolLotsService;

	@GetMapping("")
	public List<Portfolio> getAllPortfolios() {
		return portfolioRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Portfolio> getPortfolioById(@PathVariable(value = "id") Long portfolioId) throws ResourceNotFoundException 
	{
		Portfolio portfolio = this.checkPortfolio(portfolioId);
		return ResponseEntity.ok().body(portfolio);
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("")
	public Portfolio createPortfolio(@Valid @RequestBody Portfolio portfolio) 
	{
		return portfolioRepository.save(portfolio);
	}

//	@Secured({"ROLE_ADMIN","ROLE_USER"})
//	@PutMapping("/{id}")
//	public ResponseEntity<Portfolio> updatePortfolio(@PathVariable(value = "id") Long portfolioId,
//			@Valid @RequestBody Portfolio portfolioDetails) throws ResourceNotFoundException 
//	{
//		Portfolio portfolio = this.checkPortfolio(portfolioId);
//		
//		portfolio.setEmail(portfolioDetails.getEmail());
//		portfolio.setLastName(portfolioDetails.getLastName());
//		portfolio.setFirstName(portfolioDetails.getFirstName());
//		final Portfolio updatedPortfolio = portfolioRepository.save(portfolio);
//		return ResponseEntity.ok(updatedPortfolio);
//	}

	@Secured("ROLE_USER")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deletePortfolio(@PathVariable(value = "id") Long portfolioId) throws ResourceNotFoundException 
	{
		Portfolio portfolio = this.checkPortfolio(portfolioId);
		
		portfolioRepository.delete(portfolio);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	

	
	//Portfolio lots grouped by Symbol
	@GetMapping("/watchlist/{id}")
	public ResponseEntity<List<SymbolLotsDTO>> getPortfolioLotsById(@PathVariable(value = "id") Long portfolioId) throws ResourceNotFoundException 
	{
		Portfolio portfolio = this.checkPortfolio(portfolioId);
		
		return ResponseEntity.ok().body(symbolLotsService.findByPortfolio(portfolioId));
	}
	

	
	/**
	 * Busca el usuario y si no lo encuentra devuelve error
	 * @param portfolioId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private Portfolio checkPortfolio(Long portfolioId) throws ResourceNotFoundException {
		return portfolioRepository.findById(portfolioId)
				.orElseThrow(() -> new ResourceNotFoundException("Portfolio not found for this id :: " + portfolioId));
	
		
	}
}