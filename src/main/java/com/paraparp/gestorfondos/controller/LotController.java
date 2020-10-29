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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paraparp.gestorfondos.errors.exceptions.ResourceNotFoundException;
import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.repository.ILotRepository;
import com.paraparp.gestorfondos.service.ILotService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/fundapp/lots")
public class LotController {

	@Autowired
	private ILotRepository lotRepository;

	@Autowired
	private ILotService lotService;

	@GetMapping("")
	public List<LotDTO> getAllLots() {
		return lotService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<LotDTO> getLotById(@PathVariable(value = "id") Long lotId) {
		LotDTO lot = lotService.findById(lotId);
		return ResponseEntity.ok().body(lot);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PostMapping("")
	public LotDTO saveLot(@Valid @RequestBody LotDTO lot) {
		return lotService.save(lot);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PatchMapping("/")
	public ResponseEntity<LotDTO> updateLot(@Valid @RequestBody LotDTO lot) throws ResourceNotFoundException {
		LotDTO updatedLot = lotService.update(lot);
		return ResponseEntity.ok(updatedLot);
	}

	@Secured("ROLE_USER")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteLot(@PathVariable(value = "id") Long lotId) throws ResourceNotFoundException {
		Lot lot = this.checkLot(lotId);
		lotRepository.delete(lot);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * Busca el usuario y si no lo encuentra devuelve error
	 * 
	 * @param lotId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private Lot checkLot(Long lotId) throws ResourceNotFoundException {
		return lotRepository.findById(lotId)
				.orElseThrow(() -> new ResourceNotFoundException("Lot not found for this id :: " + lotId));
	}

}