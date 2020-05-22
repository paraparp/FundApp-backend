package com.paraparp.gestorfondos.service.imp;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paraparp.gestorfondos.model.dto.DailyCostDTO;
import com.paraparp.gestorfondos.model.dto.LotDTO;
import com.paraparp.gestorfondos.model.dto.PortfolioDTO;
import com.paraparp.gestorfondos.model.dto.SimpleLotDTO;
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

		List<Portfolio> portfoliosBack = this.portfolioRepo.findAll();
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

	@Override
	public List<String> listBrokersByPortfolio(Long idPortfolio) {

		Set<String> brokerList = new HashSet<String>();
		findLotsByPorfolio(idPortfolio).forEach(symbol -> brokerList.add(symbol.getBroker()));

		return new ArrayList<>(brokerList);
	}

	@Override
	public List<String> listTypesByPortfolio(Long idPortfolio) {

		Set<String> typeList = new HashSet<String>();
		findLotsByPorfolio(idPortfolio).forEach(lot -> typeList.add(lot.getSymbol().getType()));

		return new ArrayList<>(typeList);
	}

	public List<DailyCostDTO> findCostPortfolio(Long portfolioId) {

		Portfolio portfolio = portfolioRepo.findById(portfolioId).orElseThrow();
		Set<SimpleLotDTO> listLots = new HashSet<SimpleLotDTO>();
		List<Lot> lotsBack = new ArrayList<Lot>();
		lotsBack = this.lotRepo.findByPortfolio(portfolio);

		BigDecimal total = BigDecimal.ZERO;
		for (Lot lot : lotsBack) {
			total = total.add(lot.getPrice().multiply(lot.getVolume()));
			listLots.add(new SimpleLotDTO(lot.getDate(), lot.getPrice(), lot.getVolume(), lot.getPrice().multiply(lot.getVolume())));
		}
	
		List<DailyCostDTO> fridaysInRange = listOfSaturdaysFrom(LocalDate.of(2020, 3,1));

		for (DailyCostDTO dailyCost : fridaysInRange) {
			
			BigDecimal totalDay = BigDecimal.ZERO;			
			for (Lot lot : lotsBack) {
				if(lot.getDate().before(java.sql.Date.valueOf(dailyCost.getDate()))){
					totalDay= totalDay.add(lot.getPrice().multiply(lot.getVolume()));
				}
			}
			dailyCost.setTotal(totalDay);
		}

		
//		Map<Object, List<LocalDate>> maplot =
//				fridaysInRange.stream().collect(Collectors.groupingBy(w -> w));

		return  fridaysInRange;
	}

	
	
	/**
	 * crea una lista con todos los sabados de un rango
	 * @return
	 */
	private List<DailyCostDTO> listOfSaturdaysFrom(LocalDate start) {
		
		
		LocalDate end = LocalDate.now().plusDays(7);

	    DayOfWeek dowOfStart = start.getDayOfWeek();
	    int difference = DayOfWeek.SATURDAY.getValue() - dowOfStart.getValue();
	    if (difference < 0) difference += 7;

	    List<DailyCostDTO> fridaysInRange = new ArrayList<DailyCostDTO>();

	    LocalDate currentFriday = start.plusDays(difference);
	    do {
	        fridaysInRange.add(new DailyCostDTO(currentFriday,  BigDecimal.ZERO));
	        currentFriday = currentFriday.plusDays(7);
	    } while (currentFriday.isBefore(end));
	    
		return fridaysInRange;
	}

}
