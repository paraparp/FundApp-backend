package com.paraparp.gestorfondos.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.entity.Lot;
import com.paraparp.gestorfondos.model.entity.Portfolio;
import com.paraparp.gestorfondos.model.entity.User;
import com.paraparp.gestorfondos.repository.ILotRepository;
import com.paraparp.gestorfondos.repository.IPortfolioRepository;
import com.paraparp.gestorfondos.repository.IUserRepository;
import com.paraparp.gestorfondos.service.IPortfolioService;

@Service
public class PortfolioService implements IPortfolioService {

	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IPortfolioRepository portfolioRepo;

	@Autowired
	private ILotRepository lotRepo;

	@Autowired
	private IUserRepository userRepo;

	@Override
	@Transactional
	public void deleteById(Long id) {
		portfolioRepo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public PortfolioDTO findById(Long id) {

		Optional<Portfolio> portfolio = portfolioRepo.findById(id);
		return modelMapper.map(portfolio.get(), PortfolioDTO.class);
	}

	@Override
	@Transactional
	public PortfolioDTO save(PortfolioDTO portfolioDTO) {

		Portfolio portfolio = modelMapper.map(portfolioDTO, Portfolio.class);
		if (portfolioDTO.getIdUser() != null) {
			Optional<User> user = userRepo.findById(portfolioDTO.getIdUser());
			if (user.isPresent()) {
				portfolio.setUser(user.get());
			}
		}

		Portfolio saved = portfolioRepo.save(portfolio);
		return modelMapper.map(saved, PortfolioDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PortfolioDTO> findAll() {
		List<PortfolioDTO> listPortfolios = new ArrayList<PortfolioDTO>();
		List<Portfolio> portfoliosBack = new ArrayList<Portfolio>();
		portfoliosBack = this.portfolioRepo.findAll();

		for (Portfolio portfolio : portfoliosBack) {
			listPortfolios.add(this.modelMapper.map(portfolio, PortfolioDTO.class));
		}
		return listPortfolios;
	}

	@Override
	public List<PortfolioDTO> findByIdUser(Long userId) {

		User user = userRepo.getOne(userId);
		List<PortfolioDTO> listPortfolios = new ArrayList<PortfolioDTO>();
		List<Portfolio> portfoliosBack = new ArrayList<Portfolio>();
		portfoliosBack = this.portfolioRepo.findByUser(user);

		for (Portfolio portfolio : portfoliosBack) {
			listPortfolios.add(this.modelMapper.map(portfolio, PortfolioDTO.class));

		}
		return listPortfolios;
	}

	public List<LotDTO> findLotsByPorfolio(Long portfolioId) {


		Portfolio portfolio = portfolioRepo.findById(portfolioId).orElseThrow();
		List<LotDTO> listLots = new ArrayList<LotDTO>();
		List<Lot> lotsBack = new ArrayList<Lot>();
		lotsBack = this.lotRepo.findByPortfolio(portfolio);

		for (Lot lot : lotsBack) {
			listLots.add(this.modelMapper.map(lot, LotDTO.class));
		}
		return listLots;
	}

}
