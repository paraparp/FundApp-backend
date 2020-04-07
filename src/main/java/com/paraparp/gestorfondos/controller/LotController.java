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
import com.paraparp.gestorfondos.model.Lot;
import com.paraparp.gestorfondos.repository.ILotRepository;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fundapp/lots")
public class LotController 
{
	@Autowired
	private ILotRepository lotRepository;

	@GetMapping("")
	public List<Lot> getAllLots() {
		

		
		return lotRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lot> getLotById(@PathVariable(value = "id") Long lotId) throws ResourceNotFoundException 
	{
		Lot lot = this.checkLot(lotId);
		return ResponseEntity.ok().body(lot);
	}

	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@PostMapping("")
	public Lot createLot(@Valid @RequestBody Lot lot) 
	{
		return lotRepository.save(lot);
	}

//	@Secured({"ROLE_ADMIN","ROLE_USER"})
//	@PutMapping("/{id}")
//	public ResponseEntity<Lot> updateLot(@PathVariable(value = "id") Long lotId,
//			@Valid @RequestBody Lot lotDetails) throws ResourceNotFoundException 
//	{
//		Lot lot = this.checkLot(lotId);
//		
//		lot.setEmail(lotDetails.getEmail());
//		lot.setLastName(lotDetails.getLastName());
//		lot.setFirstName(lotDetails.getFirstName());
//		final Lot updatedLot = lotRepository.save(lot);
//		return ResponseEntity.ok(updatedLot);
//	}

	@Secured("ROLE_USER")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteLot(@PathVariable(value = "id") Long lotId) throws ResourceNotFoundException 
	{
		Lot lot = this.checkLot(lotId);
		
		lotRepository.delete(lot);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	


	
	/**
	 * Busca el usuario y si no lo encuentra devuelve error
	 * @param lotId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private Lot checkLot(Long lotId) throws ResourceNotFoundException {
		return lotRepository.findById(lotId)
				.orElseThrow(() -> new ResourceNotFoundException("Lot not found for this id :: " + lotId));
	
		
	}
}