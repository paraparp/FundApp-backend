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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.paraparp.gestorfondos.exception.ResourceNotFoundException;
import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.entity.Historical;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.repository.IHistoricalRepository;
import com.paraparp.gestorfondos.repository.ILotRepository;
import com.paraparp.gestorfondos.service.IHistoricalService;
import com.paraparp.gestorfondos.service.ILotService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fundapp/historical")
public class HisoricalController {
	
	@Autowired
	private IHistoricalRepository historicalRepository;

	@Autowired
	private IHistoricalService historicalService;


	
	@GetMapping("/{isin}")
	public ResponseEntity <List<Historical>> getLotById(@PathVariable(value = "isin") String isin)  {
		List<Historical> historical = historicalService.findByIsin(isin);
		return ResponseEntity.ok().body(historical);
	}



}