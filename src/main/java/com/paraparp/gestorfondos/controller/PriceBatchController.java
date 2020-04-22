package com.paraparp.gestorfondos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paraparp.gestorfondos.model.entity.Symbol;
import com.paraparp.gestorfondos.service.imp.SymbolUpdaterService;

@RestController
public class PriceBatchController {


	@Autowired
	private SymbolUpdaterService updaterService;
	


	@RequestMapping("/invokejob")
	public String handle() throws Exception {

	return this.updaterService.updater();
	}
	
	

	
}