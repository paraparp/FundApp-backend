package com.paraparp.gestorfondos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paraparp.gestorfondos.model.entity.Historical;
import com.paraparp.gestorfondos.service.IHistoricalService;

@RestController
@CrossOrigin("*")
@RequestMapping("/fundapp/historical")
public class HisoricalController {

	@Autowired
	private IHistoricalService historicalService;

	@GetMapping("/{isin}")
	public ResponseEntity<List<Historical>> getLotById(@PathVariable(value = "isin") String isin) {
		
		List<Historical> historical = historicalService.findByIsin(isin);
		
		return ResponseEntity.ok().body(historical);
	}

}