package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.Portfolio;


@Service
public interface IPortfolioService {
	
	
	public Portfolio findById(Long id);
	
	public Portfolio save(Portfolio portfolio);
	
	public void deleteById(Long id);

	public List<Portfolio> findAll();



}
