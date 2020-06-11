package com.paraparp.gestorfondos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class TestHerakuController {

	
	
	
	@GetMapping("/")
	public String test ()  {
		
		return "Funciona!!";
		
	}
}