package com.paraparp.gestorfondos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.entity.Lot;

@Service
public interface IPortfolioService {

	public PortfolioDTO findById(Long id);

	public PortfolioDTO save(PortfolioDTO portfolio);

	public void deleteById(Long id);

	public List<PortfolioDTO> findAll();

	public List<PortfolioDTO> findByIdUser(Long userId);

	public List<LotDTO> findLotsByPorfolio(Long portfolioId);

}
