package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.Portfolio;
import com.paraparp.gestorfondos.repository.IPortfolioRepository;

@Service
public class PorfolioService implements IPortfolioService {

	@Autowired
	private IPortfolioRepository porfolioRepo;
	
	@Override
	@Transactional(readOnly = true)
	public Portfolio findById(Long id) {
		return porfolioRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Portfolio save(Portfolio portfolio) {
		return porfolioRepo.save(portfolio);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		 porfolioRepo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Portfolio> findAll() {
		return porfolioRepo.findAll();
	}

	

}
