package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.DailyCostDTO;
import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;

@Service
public interface IPortfolioService {

	public PortfolioDTO findById(Long id);

	public PortfolioDTO save(PortfolioDTO portfolio);

	public void deleteById(Long id);

	public List<PortfolioDTO> findAll();

	public List<PortfolioDTO> findByIdUser(Long userId);

	public List<LotDTO> findLotsByPorfolio(Long portfolioId);

	public List<String> listBrokersByPortfolio(Long idPortfolio);

	public List<String> listTypesByPortfolio(Long idPortfolio);

	public List<DailyCostDTO> findCostPortfolio(Long portfolioId);

}
